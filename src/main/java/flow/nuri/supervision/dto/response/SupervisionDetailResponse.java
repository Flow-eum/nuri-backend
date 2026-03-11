package flow.nuri.supervision.dto.response;

import flow.nuri.supervision.domain.Supervision;

public record SupervisionDetailResponse(
        Long supervisionId
) {
    public static SupervisionDetailResponse from(Supervision supervision) {
        return new SupervisionDetailResponse(
                supervision.getId()
        );
    }
}
