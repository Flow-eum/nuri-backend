package flow.nuri.counseling.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Counseling {

    @Id @GeneratedValue
    private Long id;

    private String counselingContent;
    private String aiSummary;
    private String individualPlans;
}
