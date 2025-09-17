package umc.fitty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.fitty.domain.RunRecord;

public interface RecordRepository extends JpaRepository<RunRecord,Long> {

}
