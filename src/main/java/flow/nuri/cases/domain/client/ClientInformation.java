package flow.nuri.cases.domain.client;

import flow.nuri.cases.domain.cases.Cases;
import flow.nuri.cases.domain.client.vo.EmergencyContact;
import flow.nuri.cases.domain.client.vo.HousingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientInformation {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cases cases;

    private String clientName;
    private LocalDate clientBirthDate;
    private Integer clientAge;
    private String clientGender;
    private String clientJob;
    private String clientAddress;
    private String clientPhoneNumber;

    @Enumerated(EnumType.STRING)
    private ProtectionType protectionType;
    @Enumerated(EnumType.STRING)
    private HouseholdType householdType;

    private String disabilityType;
    private String longTermCareGrade;

    @Embedded
    private EmergencyContact emergencyContact;

    @Embedded
    private HousingStatus housingStatus;

    public void calculateAge() {
        if (this.clientBirthDate != null) {
            this.clientAge = LocalDate.now().getYear() - this.clientBirthDate.getYear() + 1;
        }
    }
}
