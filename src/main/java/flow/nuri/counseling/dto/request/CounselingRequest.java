package flow.nuri.counseling.dto.request;

import flow.nuri.counseling.domain.Counseling;

public record CounselingRequest(
        String counselingContent,
        String aiSummary,
        String individualPlans
) {
    public Counseling toEntity() {
        return Counseling.builder()
                .counselingContent(counselingContent)
                .aiSummary(aiSummary)
                .individualPlans(individualPlans)
                .build();
    }
}
