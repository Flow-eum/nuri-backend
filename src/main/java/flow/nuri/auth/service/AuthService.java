package flow.nuri.auth.service;

import flow.nuri.auth.domain.User;
import flow.nuri.auth.domain.UserRoleEnum;
import flow.nuri.auth.dto.SignUpReq;
import flow.nuri.auth.repository.UserRepository;
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
                .password(password)
                .role(role)
                .build();

        userRepository.save(user);

    }
}
