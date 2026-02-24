package flow.nuri.auth.controller;

import flow.nuri.auth.dto.LoginReq;
import flow.nuri.auth.dto.SignUpReq;
import flow.nuri.auth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signup(@RequestBody SignUpReq req) {
        authService.signUp(req);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq req, HttpServletResponse res) {
        authService.login(req, res);
        return ResponseEntity.ok().build();
    }
}
