package glucobot.glucobotapi.data.repository;

import glucobot.glucobotapi.data.model.Category;
import glucobot.glucobotapi.data.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @EntityGraph(value = "UserWithCategory")
    Optional<User> findByEmail(String email);

    @EntityGraph(value = "User")
    @Query("select u from User u where u.category.id = " + Category.PATIENT + " order by u.lastName asc")
    List<User> findAllPatients();

    @EntityGraph(value = "UserWithCategory")
    @Query("select u from User u where u.id=:id and u.category.id = " + Category.PATIENT)
    Optional<User> findPatientById(@Param("id") long id);

}
