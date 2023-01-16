package canard.intern.post.following.backend.repository;

import canard.intern.post.following.backend.entity.Trainee;
import canard.intern.post.following.backend.enums.PoeType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TraineeRepository extends JpaRepository<Trainee,Integer> {

    // JPQL auto generated according to Spring Data Jpa Vocabulary
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
    List<Trainee> findByPoeId(int poeId);
    
//    Optional<Trainee> findByPoeType(String type);

    List<Trainee> findByLastnameIgnoreCase(String partialName);

    List<Trainee> findByPoeTitleIgnoreCase(String title);

}
