package flow.nuri.cases.domain.client.vo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HousingStatus {

    @Enumerated(EnumType.STRING)
    private HousingType housingType;

    private String housingTypeOther;

    @Enumerated(EnumType.STRING)
    private OwnershipType ownershipType;

    private String ownershipOther;
}
