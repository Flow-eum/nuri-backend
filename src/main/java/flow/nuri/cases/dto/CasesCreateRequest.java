package flow.nuri.cases.dto;

import flow.nuri.cases.domain.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Builder
public class CasesCreateRequest {
    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    private String caseNumber;

    @NotNull(message = "사정일자를 필수입니다.")
    private LocalDate assessmentDate;

    private AssessmentType assessmentType;

    // 기본 인적사항
    private LocalDate birthDate;
    private Integer age;
    private String gender;
    private String job;
    private String address;
    private String phoneNumber;

    private EmergencyContact emergencyContact;
    private ProtectionType protectionType;
    private HouseholdType householdType;
    private HousingInfo housingInfo;

    private String disabilityType;
    private String longTermCareGrade;

    private List<MeetingLog> meetingLogs = new ArrayList<>();
    private List<FamilyMember> familyMembers = new ArrayList<>();


    public Cases toEntity() {
        return Cases.builder()
                .name(name)
                .caseNumber(caseNumber)
                .assessmentDate(assessmentDate)
                .birthDate(birthDate)
                .age(age)
                .gender(gender)
                .job(job)
                .address(address)
                .phoneNumber(phoneNumber)
                .emergencyContact(emergencyContact)
                .protectionType(protectionType)
                .householdType(householdType)
                .housingInfo(housingInfo)
                .disabilityType(disabilityType)
                .longTermCareGrade(longTermCareGrade)
                .build();
    }

    @Getter @Setter
    public static class MeetingLogsRequest {
        private Integer sequence;
        private LocalDateTime meetingDateTime;
        private String method; // 전화, 방문, 내방 등
        private String interviewer;
        private String interviewee;

        public MeetingLog toEntity() {
            return MeetingLog.builder()
                    .sequence(sequence)
                    .meetingDateTime(meetingDateTime)
                    .method(method)
                    .interviewer(interviewer)
                    .interviewee(interviewee)
                    .build();
        }
    }

    @Getter @Setter
    public static class FamilyMembersRequest {
        private String relationship;
        private String familyName;
        private String gender;
        private LocalDate birthDate;
        private String job;
        private boolean isLivingTogether;
        private String etc;

        public FamilyMember toEntity() {
            return FamilyMember.builder()
                    .relationship(relationship)
                    .familyName(familyName)
                    .gender(gender)
                    .birthDate(birthDate)
                    .job(job)
                    .isLivingTogether(isLivingTogether)
                    .etc(etc)
                    .build();
        }
    }
}
