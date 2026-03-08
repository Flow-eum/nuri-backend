package flow.nuri.auth.service;

import flow.nuri.auth.domain.User;
import flow.nuri.auth.dto.response.GetUserInfoResponse;
import flow.nuri.auth.repository.UserRepository;
import flow.nuri.auth.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public GetUserInfoResponse getMyInfo(UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자 정보를 찾을 수 없습니다."));

        GetUserInfoResponse getMyInfo = new GetUserInfoResponse();
        getMyInfo.setUsername(user.getUsername());

        return getMyInfo;
    }
}
