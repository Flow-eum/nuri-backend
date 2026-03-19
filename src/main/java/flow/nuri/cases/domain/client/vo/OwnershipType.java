package flow.nuri.cases.domain.client.vo;

public enum OwnershipType {
    SELF_OWNED("자가"),
    JEONSE("전세"),
    PUBLIC_RENTAL("임대"),
    MONTHLY_RENT("월세"),
    OTHER("기타");

    private final String description;

    OwnershipType(String description) {this.description = description;}
}
