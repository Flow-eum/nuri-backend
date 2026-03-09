package flow.nuri.supervision.controller;

import flow.nuri.supervision.dto.request.SupervisionRequest;
import flow.nuri.supervision.service.SupervisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> acceptSupervision(@PathVariable("supervisionId") Long supervisionId) {
        supervisionService.acceptSupervision(supervisionId);
        return ResponseEntity.ok().build();
    }
}
