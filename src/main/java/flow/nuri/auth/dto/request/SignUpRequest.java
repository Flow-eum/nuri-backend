package flow.nuri.auth.dto.request;

import flow.nuri.auth.domain.User;
import flow.nuri.auth.domain.UserRoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
        @NotBlank(message = "사용자명은 필수입니다.")
        @Size(min = 4, max = 15)
        String username,

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$", message = "비밀번호는 영문, 숫자 포함 8자 이상이어야 합니다.")
        String password,
        UserRoleEnum role,
        String adminToken
) {
    public User toEntity(String encodedPassword) {
        return User.builder()
                .username(username)
                .password(encodedPassword)
                .role(role != null ? role: UserRoleEnum.USER)
                .build();
    }
}
