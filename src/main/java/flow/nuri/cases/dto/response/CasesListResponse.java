package flow.nuri.cases.dto.response;

import flow.nuri.cases.domain.Cases;

import java.time.LocalDate;

public record CasesListResponse(
        Long caseId,
        String name,
        LocalDate birthDate,
        Integer age
) {
    public static CasesListResponse from(Cases cases) {
        return new CasesListResponse(
                cases.getId(),
                cases.getName(),
                cases.getBirthDate(),
                cases.getAge()
        );
    }
}
