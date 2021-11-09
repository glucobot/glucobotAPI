package glucobot.glucobotapi.data.repository;

import glucobot.glucobotapi.data.model.Measure;
import org.springframework.data.repository.CrudRepository;

public interface MeasureRepository extends CrudRepository<Measure, Long> {
}
