package flow.nuri.cases.service;

import flow.nuri.cases.domain.Cases;
import flow.nuri.cases.dto.CasesCreateRequest;
import flow.nuri.cases.repository.CaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CaseService {

    private final CaseRepository caseRepository;

    public Long createCases(CasesCreateRequest request) {
        Cases cases = request.toEntity();

        return caseRepository.save(cases).getId();
    }
}
