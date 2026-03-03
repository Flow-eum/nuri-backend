package flow.nuri.ai.dto.response;

public record SummaryResponse(
        Long contentId,
        String summary,
        String status
) {
}
