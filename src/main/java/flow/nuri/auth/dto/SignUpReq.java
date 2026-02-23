package flow.nuri.auth.dto;

import flow.nuri.auth.domain.UserRoleEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpReq {

    private String username;
    private String password;
    private UserRoleEnum role;
    private String adminToken;
}
