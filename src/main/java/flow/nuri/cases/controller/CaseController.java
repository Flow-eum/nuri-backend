package flow.nuri.cases.controller;

import flow.nuri.cases.dto.request.CasesRequest;
import flow.nuri.cases.dto.response.CasesDetailResponse;
import flow.nuri.cases.dto.response.CasesListResponse;
import flow.nuri.cases.service.CaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/cases")
@RestController
@RequiredArgsConstructor
public class CaseController {

    private final CaseService caseService;

    @PostMapping
    public ResponseEntity<?> createCase(@RequestBody CasesRequest req) {
        caseService.createCases(req);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllCases() {
        List<CasesListResponse> responses = caseService.getAllCases();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{caseId}")
    public ResponseEntity<?> getCaseById(@PathVariable("caseId") Long caseId) {
        CasesDetailResponse response = caseService.getCaseById(caseId);
        return ResponseEntity.ok(response);
    }
}
