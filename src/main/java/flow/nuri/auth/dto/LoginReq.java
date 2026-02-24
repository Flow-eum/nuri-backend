package flow.nuri.auth.dto;

import lombok.Data;

@Data
public class LoginReq {

    private String username;
    private String password;
}
