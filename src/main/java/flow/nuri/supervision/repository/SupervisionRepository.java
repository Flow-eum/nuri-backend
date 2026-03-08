package flow.nuri.supervision.repository;

import flow.nuri.supervision.domain.Supervision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupervisionRepository extends JpaRepository<Supervision, Long> {
}
