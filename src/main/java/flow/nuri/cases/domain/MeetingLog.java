package flow.nuri.cases.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeetingLog {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cases cases;

    private Integer sequence; // 1차, 2차, 3차
    private LocalDateTime meetingDateTime;
    private String method; // 전화, 방문, 내방 등
    private String interviewer;
    private String interviewee;
}
