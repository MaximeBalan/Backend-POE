	package canard.intern.post.following.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import canard.intern.post.following.backend.entity.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Integer>{
	
}
