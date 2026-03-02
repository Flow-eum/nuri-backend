package flow.nuri.documents.dto.response;

import flow.nuri.documents.domain.Document;

public record DocumentDetailResponse(
        Long documentId,
        String title,
        String content
) {
    public static DocumentDetailResponse from(Document document) {
        return new DocumentDetailResponse(
                document.getId(),
                document.getTitle(),
                document.getContent()
        );
    }
}
