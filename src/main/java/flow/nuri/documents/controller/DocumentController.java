package flow.nuri.documents.controller;

import flow.nuri.documents.dto.request.DocumentRequest;
import flow.nuri.documents.dto.response.DocumentDetailResponse;
import flow.nuri.documents.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/cases/{caseId}/documents")
@RestController
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping
    public ResponseEntity<?> createCaseDocument(@RequestBody DocumentRequest req) {
        documentService.createCaseDocument(req);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<?> getCaseDocument(@PathVariable("documentId") Long documentId) {
        DocumentDetailResponse response = documentService.getDocumentById(documentId);
        return ResponseEntity.ok(response);
    }
}
