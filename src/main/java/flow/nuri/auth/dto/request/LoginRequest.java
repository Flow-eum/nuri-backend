package flow.nuri.auth.dto.request;

import flow.nuri.auth.domain.User;

public record LoginRequest(
        String username,
        String password
) {
}
