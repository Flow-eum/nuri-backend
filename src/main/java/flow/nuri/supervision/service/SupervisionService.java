package flow.nuri.supervision.service;

import flow.nuri.auth.domain.User;
import flow.nuri.auth.repository.UserRepository;
import flow.nuri.common.auth.SecurityProvider;
import flow.nuri.supervision.domain.Supervision;
import flow.nuri.supervision.domain.SupervisionStatus;
import flow.nuri.supervision.dto.request.SupervisionRequest;
import flow.nuri.supervision.dto.response.SupervisionResponse;
import flow.nuri.supervision.repository.SupervisionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SupervisionService {

    private final UserRepository userRepository;
    private final SupervisionRepository supervisionRepository;
    private final SecurityProvider securityProvider;

    public void requestSupervision(SupervisionRequest request) {
        String currentUsername = securityProvider.getCurrentUsername();

        User requester = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new EntityNotFoundException("로그인 유저를 찾을 수 없습니다."));

        User supervisor = userRepository.findByUsername(request.supervisor())
                .orElseThrow(() -> new EntityNotFoundException("대상 유저를 찾을 수 없습니다."));

        Supervision supervision = Supervision.builder()
                .requester(requester)
                .supervisor(supervisor)
                .status(SupervisionStatus.PENDING)
                .build();

        supervisionRepository.save(supervision);
    }




}
