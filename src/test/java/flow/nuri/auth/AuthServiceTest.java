package flow.nuri.auth;

import flow.nuri.auth.domain.User;
import flow.nuri.auth.domain.UserRoleEnum;
import flow.nuri.auth.dto.SignUpReq;
import flow.nuri.auth.repository.UserRepository;
import flow.nuri.auth.service.AuthService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class AuthServiceTest {

    @Autowired UserRepository userRepository;
    @Autowired AuthService authService;

    @Test
    @Transactional // 테스트 후 롤백해줌
    void signUpTest() {
        // given
        UserRoleEnum role = UserRoleEnum.USER;

        SignUpReq req = SignUpReq.builder()
                .username("james")
                .password("1234")
                .role(role)
                .build();

        // when
        authService.signUp(req);
        Optional<User> signedUser = userRepository.findByUsername("james");
        // then
        assertThat(signedUser)
                .isPresent() // 값이 존재하는지 확인
                .hasValueSatisfying(user -> {
                    assertThat(user.getUsername()).isEqualTo(req.getUsername());
                    assertThat(user.getRole()).isEqualTo(req.getRole());
                    assertThat(user.getPassword()).isEqualTo(req.getPassword());
                });
    }
}
