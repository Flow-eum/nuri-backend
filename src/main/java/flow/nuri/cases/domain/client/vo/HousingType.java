package flow.nuri.cases.domain.client.vo;

public enum HousingType {
    APARTMENT("아파트"),
    HOUSE("주택"),
    ONE_ROOM("원룸"),
    GOSHIWON("고시원"),
    OTHER("기타");

    private final String description;

    HousingType(String description) {this.description = description;}
}
