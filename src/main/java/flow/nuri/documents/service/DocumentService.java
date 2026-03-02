package flow.nuri.documents.service;

import flow.nuri.documents.domain.Document;
import flow.nuri.documents.dto.request.DocumentRequest;
import flow.nuri.documents.dto.response.DocumentDetailResponse;
import flow.nuri.documents.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    public Long createCaseDocument(DocumentRequest req) {
        Document document = req.toEntity();
        Document savedDocument = documentRepository.save(document);

        return savedDocument.getId();
    }

    @Transactional(readOnly = true)
    public DocumentDetailResponse getDocumentById(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 내용을 찾을 수 없습니다" + id));

        return DocumentDetailResponse.from(document);
    }

}
