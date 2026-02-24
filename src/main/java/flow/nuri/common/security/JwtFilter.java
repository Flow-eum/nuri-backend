package flow.nuri.common.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret.key}")
    private String secretKey;

    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        String url = request.getRequestURI();

        if (isAuthorizationPassRequest(url)) {
            log.info("인증 제외 API 요청: {}", url);
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 확인 및 검증 로직 유지
        String token = getTokenFromRequest(request);
        log.info("요청에서 추출된 토큰: {}", token);

        if (StringUtils.hasText(token)) {
            SecretKey key = getSecretKey();

            if (!validateToken(token, key)) {
                throw new IllegalArgumentException("Token Error");
            }

            Claims claims = getUserInfoFromToken(token, key);
            String username = claims.getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.info("인증 완료: {}", SecurityContextHolder.getContext().getAuthentication());
        } else {
            log.warn("토큰이 요청에 포함되지 않았습니다.");
        }

        filterChain.doFilter(request, response);
    }

    // 토큰 검증
    public boolean validateToken(String token, SecretKey key) {
        if (token == null || token.isEmpty()) {
            log.error("토큰 값이 null 또는 비어있습니다.");
            return false;
        }
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            log.info("토큰 검증 설공");
            return true;
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return false;
    }

    public String getTokenFromRequest(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                log.info("쿠키 이름: {}, 쿠키 값: {}", cookie.getName(), cookie.getValue());
                if (cookie.getName().equals("")) {
                    String cookieValue = cookie.getValue();
                    if (cookieValue != null) {
                        log.info("디코딩된 토큰: {}", cookieValue);
                        return cookieValue;
                    } else {
                        log.error("쿠키 값이 null 입니다.");
                    }
                }
            }
        }
    }

    public Claims getUserInfoFromToken(String token, SecretKey key) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    private boolean isAuthorizationPassRequest(String path) {
        return path.startsWith("/api/v1/auth/login") || path.startsWith("/api/v1/auth/sign-up");
    }

    private SecretKey getSecretKey() {
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalStateException("JwtFilter에 secretKey가 null입니다.");
        }
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
}
