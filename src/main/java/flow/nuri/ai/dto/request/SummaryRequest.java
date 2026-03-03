package flow.nuri.ai.dto.request;

import flow.nuri.counseling.domain.Counseling;

public record SummaryRequest(
        Long counselingId,
        String counselingContent
) {
    public Counseling toEntity() {
        return Counseling.builder()
                .id(counselingId)
                .aiSummary(counselingContent)
                .build();
    }
}
