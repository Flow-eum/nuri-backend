package flow.nuri.cases.repository;

import flow.nuri.cases.domain.Cases;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<Cases, Long> {

}
