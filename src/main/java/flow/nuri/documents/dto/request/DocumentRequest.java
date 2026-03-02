package flow.nuri.documents.dto.request;

import flow.nuri.documents.domain.Document;
import jakarta.validation.constraints.NotBlank;

public record DocumentRequest(
        @NotBlank(message = "제목은 필수입니다.")
        String title,
        @NotBlank(message = "본문은 필수입니다.")
        String content
) {
    public Document toEntity() {
        return Document.builder()
                .title(title)
                .content(content)
                .build();
    }

    public static DocumentRequest from(Document document) {
        return new DocumentRequest(
                document.getTitle(),
                document.getContent()
        );

    }
}
