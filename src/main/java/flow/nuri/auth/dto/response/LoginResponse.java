package flow.nuri.auth.dto.response;

public record LoginResponse(
        String username,
        String message
) {
    public static LoginResponse of(String username) {
        return new LoginResponse(username, "로그인이 완료되었습니다.");
    }
}
