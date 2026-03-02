package flow.nuri.counseling.dto.response;

import flow.nuri.counseling.domain.Counseling;

public record CounselingListResponse(
        Long counselingId
) {
    public static CounselingListResponse from(Counseling counseling) {
        return new CounselingListResponse(
                counseling.getId()
        );
    }
}
