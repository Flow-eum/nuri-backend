package flow.nuri.cases.domain.cases;

import flow.nuri.cases.domain.client.ClientInformation;
import flow.nuri.cases.domain.client.FamilyMember;
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

    private String caseNumber;
    private LocalDate assessmentDate;

    @Enumerated(EnumType.STRING)
    private AssessmentType assessmentType;

    @OneToMany(mappedBy = "cases", cascade = CascadeType.ALL)
    private List<MeetingLog> meetingLogs = new ArrayList<>();

    @OneToMany(mappedBy = "cases", cascade = CascadeType.ALL)
    private List<ClientInformation> clientInformationList = new ArrayList<>();

    @OneToMany(mappedBy = "cases", cascade = CascadeType.ALL)
    private List<FamilyMember> familyMembers = new ArrayList<>();
}
