package flow.nuri.auth.controller;

import flow.nuri.auth.dto.GetUserInfoRes;
import flow.nuri.auth.security.UserDetailsImpl;
import flow.nuri.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/info/me")
    public ResponseEntity<GetUserInfoRes> getMyInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        GetUserInfoRes myInfo = userService.getMyInfo(userDetails);
        return ResponseEntity.ok(myInfo); // 현재 인증된 사용자 정보 반환
    }
}
