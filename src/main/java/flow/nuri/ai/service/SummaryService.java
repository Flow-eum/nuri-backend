package flow.nuri.ai.service;

import flow.nuri.ai.dto.request.SummaryRequest;
import flow.nuri.counseling.dto.response.CounselingDetailResponse;
import flow.nuri.counseling.service.CounselingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class SummaryService {

    private final WebClient fastApiClient;
    private final CounselingService counselingService;

    public void postSummaryRequest(Long counselingId) {
        String url = "";
        CounselingDetailResponse record = counselingService.getCounselingById(counselingId);
        SummaryRequest dto = new SummaryRequest(counselingId, record.counselingContent());
        log.info("FastAPI로 요약 전송 시작: recordId={}", dto.counselingId());

        // 비동기 시작
        fastApiClient.post()
                .uri(url)
                .bodyValue(dto)
                .retrieve()
                .toBodilessEntity()
                .doOnSuccess(response -> log.info("FastAPI 접수 완료: counselingId={}", dto.counselingId()))
                .doOnError(e -> log.error("전송 중 에러 발생: {}", e.getMessage()))
                .subscribe();

        log.info("FastAPI 응답을 기다리지 않고 즉시 다음 로직 수행");
    }
}
