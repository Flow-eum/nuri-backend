package flow.nuri.supervision.dto.response;

import flow.nuri.supervision.domain.Supervision;

public record SupervisionListResponse(
        Long supervisionId
) {
    public static SupervisionListResponse from(Supervision supervision) {
        return new SupervisionListResponse(
                supervision.getId()
        );
    }
}
