package flow.nuri.cases.domain;

public enum ProtectionType {
    CUSTOMIZED_BENEFITS("맞춤형급여(생계/의료/주거/교육)"),
    CONDITIONAL_BENEFITS("조건부수급"),
    NEAR_POVERTY("차상위"),
    LOW_INCOME("저소득"),
    GENERAL("일반");

    private final String description;

    ProtectionType(String description) {
        this.description = description;
    }
}
