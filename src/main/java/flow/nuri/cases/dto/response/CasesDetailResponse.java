package flow.nuri.cases.dto.response;

import flow.nuri.cases.domain.AssessmentType;
import flow.nuri.cases.domain.Cases;

import java.time.LocalDate;

public record CasesDetailResponse(
        Long caseId,
        String name,
        String caseNumber,
        LocalDate birthDate,
        Integer age,
        String gender,
        String job,
        String address,
        String phoneNumber,
        AssessmentType assessmentType
) {
    public static CasesDetailResponse from(Cases cases) {
        return new CasesDetailResponse(
                cases.getId(),
                cases.getName(),
                cases.getCaseNumber(),
                cases.getBirthDate(),
                cases.getAge(),
                cases.getGender(),
                cases.getJob(),
                cases.getAddress(),
                cases.getPhoneNumber(),
                cases.getAssessmentType()
        );
    }
}
