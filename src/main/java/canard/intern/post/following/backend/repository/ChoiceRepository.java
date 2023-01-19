package canard.intern.post.following.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import canard.intern.post.following.backend.entity.Choice;
import canard.intern.post.following.backend.entity.Trainee;


public interface ChoiceRepository extends JpaRepository<Choice,Integer>  {

	 @Query(value = "SELECT * FROM choices c WHERE c.question_id = :id", nativeQuery = true)
	 List<Choice> findByQuestionId(@Param("id") int id);

}
