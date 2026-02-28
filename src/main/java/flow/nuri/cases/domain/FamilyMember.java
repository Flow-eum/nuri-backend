package flow.nuri.cases.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FamilyMember {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cases cases;

    private String relationship;
    private String familyName;
    private String gender;
    private LocalDate birthDate;
    private String job;
    private boolean isLivingTogether;

    private String etc; // 특이사항

}
