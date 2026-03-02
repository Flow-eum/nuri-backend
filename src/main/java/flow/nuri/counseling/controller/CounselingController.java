package flow.nuri.counseling.controller;

import flow.nuri.counseling.dto.request.CounselingRequest;
import flow.nuri.counseling.dto.response.CounselingDetailResponse;
import flow.nuri.counseling.dto.response.CounselingListResponse;
import flow.nuri.counseling.service.CounselingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cases/{caseId}/counseling")
@RequiredArgsConstructor
public class CounselingController {

    private final CounselingService counselingService;

    @PostMapping
    public ResponseEntity<?> createCounseling(@RequestBody CounselingRequest req) {
        counselingService.createCounseling(req);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllCounseling() {
        List<CounselingListResponse> responses = counselingService.getAllCounseling();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{counselingId}")
    public ResponseEntity<?> getCounseling(@PathVariable("counselingId") Long counselingId) {
        CounselingDetailResponse response = counselingService.getCounselingById(counselingId);
        return ResponseEntity.ok(response);
    }

}
