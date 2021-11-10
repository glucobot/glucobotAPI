package glucobot.glucobotapi.data.repository;

import glucobot.glucobotapi.data.model.Measure;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MeasureRepository extends CrudRepository<Measure, Long> {

    @Query("select m from Measure m where m.userId = :user_id and m.timestamp > :timestamp order by m.timestamp desc")
    List<Measure> getMeasuresForUser(
            @Param("user_id") long userId,
            @Param("timestamp") LocalDateTime timestamp,
            Pageable pageable);
}
