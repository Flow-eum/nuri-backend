package flow.nuri.counseling.dto.response;

import flow.nuri.counseling.domain.Counseling;

public record CounselingDetailResponse(
        Long counselingId,
        String counselingContent,
        String aiSummary,
        String individualPlans
) {
    public static CounselingDetailResponse from(Counseling counseling) {
        return new CounselingDetailResponse(
                counseling.getId(),
                counseling.getCounselingContent(),
                counseling.getAiSummary(),
                counseling.getIndividualPlans()
        );
    }
}
