package flow.nuri.cases.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class HousingInfo {
    private String type; // 아파트, 주택, 원룸 등
    private String ownership; // 자가, 전세, 월세 등
}
