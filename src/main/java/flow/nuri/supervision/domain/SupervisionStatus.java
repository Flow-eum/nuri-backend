package flow.nuri.supervision.domain;

public enum SupervisionStatus {
    PENDING("대기중"),
    ACCEPTED("승인됨"),
    REJECTED("거절됨");

    private final String description;

    SupervisionStatus(String description) {this.description = description;}
}
