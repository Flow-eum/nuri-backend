package flow.nuri.cases.service;

import flow.nuri.cases.domain.Cases;
import flow.nuri.cases.dto.request.CasesRequest;
import flow.nuri.cases.dto.response.CasesDetailResponse;
import flow.nuri.cases.dto.response.CasesListResponse;
import flow.nuri.cases.repository.CaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CaseService {

    private final CaseRepository caseRepository;

    public Long createCases(CasesRequest request) {
        Cases cases = request.toEntity();

        return caseRepository.save(cases).getId();
    }

    @Transactional(readOnly = true)
    public List<CasesListResponse> getAllCases() {
        return caseRepository.findAll().stream()
                .map(CasesListResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CasesDetailResponse getCaseById(Long id) {
        Cases cases = caseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사례를 찾을 수 없습니다" + id));

        return CasesDetailResponse.from(cases);
    }
}
