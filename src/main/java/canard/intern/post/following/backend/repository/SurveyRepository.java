package canard.intern.post.following.backend.repository;

import canard.intern.post.following.backend.entity.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;

import canard.intern.post.following.backend.entity.Survey;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Integer>{

   // List<Survey> findBySurveyId(int surveyId);
	
}
