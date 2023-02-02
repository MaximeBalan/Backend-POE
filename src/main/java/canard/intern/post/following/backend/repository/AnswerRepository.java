package canard.intern.post.following.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import canard.intern.post.following.backend.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {


}
