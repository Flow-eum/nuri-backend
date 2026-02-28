package flow.nuri.cases.domain;

public enum HouseholdType {
    SINGLE("1인가구"),
    ELDERLY_COUPLE("노인부부"),
    PARENTS_CHILDREN("부모자녀"),
    SINGLE_PARENT("한부모"),
    GRANDPARENTS_GRANDCHILDREN("조손"),
    MULTICULTURAL("다문화"),
    OTHERS("기타");

    private final String description;

    HouseholdType(String description) {
        this.description = description;
    }
}
