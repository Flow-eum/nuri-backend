package flow.nuri.supervision.domain;

import flow.nuri.auth.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Supervision {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private User requester;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private User supervisor;

    @Enumerated(EnumType.STRING)
    private SupervisionStatus status = SupervisionStatus.PENDING; // Pending, Accepted, Rejected

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime requestAt;
    private LocalDateTime acceptedAt;

    public void accept() {
        this.status = SupervisionStatus.ACCEPTED;
        this.acceptedAt = LocalDateTime.now();
    }
}
