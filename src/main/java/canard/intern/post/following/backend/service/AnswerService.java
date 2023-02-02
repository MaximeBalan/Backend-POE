package canard.intern.post.following.backend.service;

import java.util.List;
import java.util.Optional;
import canard.intern.post.following.backend.dto.AnswerDto;

public interface AnswerService {
	
	List<AnswerDto> getAll();
	
	Optional<AnswerDto> getById(int id);
	
	AnswerDto create(AnswerDto answerDto);

}
