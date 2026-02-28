package flow.nuri.cases.domain;

public enum AssessmentType {
    INITIAL("신규"),
    RE_ASSESSMENT("재사정");

    private final String description;

    AssessmentType(String description) {
        this.description = description;
    }
}
