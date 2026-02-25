package flow.nuri.auth.service;

import flow.nuri.common.security.JwtProvider;
import flow.nuri.auth.domain.User;
import flow.nuri.auth.domain.UserRoleEnum;
import flow.nuri.auth.dto.LoginReq;
import flow.nuri.auth.dto.SignUpReq;
import flow.nuri.auth.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final String ADMIN_TOKEN = "adminToken";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public void signUp(SignUpReq req) {
        String username = req.getUsername();
        String password = req.getPassword();
        UserRoleEnum role = req.getRole();

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        if (role == null) {
            role = UserRoleEnum.USER;
        } else if(role == UserRoleEnum.ADMIN) {
            throw new IllegalArgumentException("유효한 관리자 토큰이 필요합니다.");
        }

        // 사용자 등록
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password)) // 보안을 위한 encoding
                .role(role)
                .build();

        userRepository.save(user);
    }

    @Transactional
    public void login(LoginReq req, HttpServletResponse res) {
        String username = req.getUsername();
        String password = req.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        // 비밀번호 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // JWT 생성 및 쿠키에 저장한 후 Response 객체에 추가
        String token = jwtProvider.createToken(user.getUsername(), user.getRole());
        jwtProvider.addJwtToCookie(token, res);
    }
}
