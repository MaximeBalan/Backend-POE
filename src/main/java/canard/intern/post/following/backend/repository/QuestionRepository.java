package canard.intern.post.following.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import canard.intern.post.following.backend.entity.Question;

public interface QuestionRepository extends JpaRepository<Question,Integer>{

	 List<Question> findByTitle(String title);
	 
	 @Query(value = "SELECT * FROM questions q WHERE q.question_type = :type", nativeQuery = true)
	 List<Question> getByQuestionType(@Param("type") String type);
}
