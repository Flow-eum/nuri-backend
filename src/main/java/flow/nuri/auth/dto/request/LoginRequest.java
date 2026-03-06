package flow.nuri.auth.dto.request;

import flow.nuri.auth.domain.User;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "사용자명을 입력해주세요.")
        String username,
        @NotBlank(message = "비밀번호를 입력해주세요.")
        String password
) {
}
