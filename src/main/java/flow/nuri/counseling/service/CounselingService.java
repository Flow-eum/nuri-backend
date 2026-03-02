package flow.nuri.counseling.service;

import flow.nuri.counseling.domain.Counseling;
import flow.nuri.counseling.dto.request.CounselingRequest;
import flow.nuri.counseling.dto.response.CounselingDetailResponse;
import flow.nuri.counseling.dto.response.CounselingListResponse;
import flow.nuri.counseling.repository.CounselingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CounselingService {

    private final CounselingRepository counselingRepository;

    public Long createCounseling(CounselingRequest req) {
        Counseling counseling = req.toEntity();
        return counselingRepository.save(counseling).getId();
    }

    @Transactional(readOnly = true)
    public List<CounselingListResponse> getAllCounseling() {
        return counselingRepository.findAll().stream()
                .map(CounselingListResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CounselingDetailResponse getCounselingById(Long counselingId) {
        Counseling counseling = counselingRepository.findById(counselingId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상담 기록을 찾을 수 없습니다." + counselingId));

        return CounselingDetailResponse.from(counseling);
    }
}
