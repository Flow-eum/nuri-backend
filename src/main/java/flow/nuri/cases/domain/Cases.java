package flow.nuri.cases.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "cases")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cases {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String caseNumber; // 사례 번호
    private LocalDate assessmentDate; // 사정일자

    @Enumerated(EnumType.STRING)
    private AssessmentType assessmentType; // 신규/재사정

    // 기본 인적사항
    private LocalDate birthDate;
    private Integer age;
    private String gender;
    private String job;
    private String address;
    private String phoneNumber;

    // 비상 연락처
    @Embedded
    private EmergencyContact emergencyContact;

    // 보호 및 가구 정보
    @Enumerated(EnumType.STRING)
    private ProtectionType protectionType;
    @Enumerated(EnumType.STRING)
    private HouseholdType householdType;

    // 주거 현황
    @Embedded
    private HousingInfo housingInfo;

    // 장애 및 장기요양
    private String disabilityType;
    private String longTermCareGrade;

    @OneToMany(mappedBy = "cases", cascade = CascadeType.ALL)
    private List<MeetingLog> meetingLogs = new ArrayList<>();

    @OneToMany(mappedBy = "cases", cascade = CascadeType.ALL)
    private List<FamilyMember> familyMembers = new ArrayList<>();
}
