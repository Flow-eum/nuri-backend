package flow.nuri.counseling.repository;

import flow.nuri.counseling.domain.Counseling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounselingRepository extends JpaRepository<Counseling, Long> {
}
