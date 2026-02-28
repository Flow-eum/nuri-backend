package flow.nuri.cases.controller;

import flow.nuri.cases.dto.CasesCreateRequest;
import flow.nuri.cases.service.CaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/cases")
@RestController
@RequiredArgsConstructor
public class CaseController {

    private final CaseService caseService;

    @PostMapping
    public ResponseEntity<?> createCase(@RequestBody CasesCreateRequest req) {
        caseService.createCases(req);
        return ResponseEntity.ok().build();
    }
}
