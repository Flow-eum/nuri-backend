package flow.nuri.ai.controller;

import flow.nuri.ai.dto.request.SummaryRequest;
import flow.nuri.ai.service.SummaryService;
import flow.nuri.counseling.dto.response.CounselingDetailResponse;
import flow.nuri.counseling.service.CounselingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cases/{caseId}/counseling")
@RequiredArgsConstructor
public class SummaryController {

    private final SummaryService summaryService;

    @PostMapping("/{counselingId}/summarize")
    public ResponseEntity<?> requestCounselingSummary(@PathVariable Long counselingId) {
        summaryService.postSummaryRequest(counselingId);
        return ResponseEntity.accepted().body("케이스" + counselingId + "번에 대한 요청이 접수되었습니다.");
    }
}
