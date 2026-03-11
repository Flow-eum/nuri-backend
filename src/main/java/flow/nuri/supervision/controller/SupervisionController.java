package flow.nuri.supervision.controller;

import flow.nuri.supervision.dto.request.SupervisionRequest;
import flow.nuri.supervision.dto.response.SupervisionDetailResponse;
import flow.nuri.supervision.dto.response.SupervisionListResponse;
import flow.nuri.supervision.service.SupervisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supervision")
@RequiredArgsConstructor
public class SupervisionController {

    private final SupervisionService supervisionService;

    @PostMapping
    public ResponseEntity<?> requestSupervision(@RequestBody SupervisionRequest request) {
        supervisionService.requestSupervision(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/accept")
    public ResponseEntity<?> acceptSupervision(Long supervisionId) {
        supervisionService.acceptSupervision(supervisionId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllSupervision() {
        List<SupervisionListResponse> responses = supervisionService.getAllSupervision();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{supervisionId}")
    public ResponseEntity<?> getSupervision(@PathVariable("supervisionId") Long supervisionId) {
        SupervisionDetailResponse response = supervisionService.getSupervisionById(supervisionId);
        return ResponseEntity.ok(response);
    }
}