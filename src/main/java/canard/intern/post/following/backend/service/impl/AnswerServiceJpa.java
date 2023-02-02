package canard.intern.post.following.backend.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import canard.intern.post.following.backend.dto.AnswerDto;
import canard.intern.post.following.backend.dto.ChoiceDto;
import canard.intern.post.following.backend.dto.PoeDetailDto;
import canard.intern.post.following.backend.dto.SurveyDetailDto;
import canard.intern.post.following.backend.dto.SurveyDto;
import canard.intern.post.following.backend.dto.TraineeDetailDto;
import canard.intern.post.following.backend.dto.TraineeDto;
import canard.intern.post.following.backend.entity.Answer;
import canard.intern.post.following.backend.error.UpdateException;
import canard.intern.post.following.backend.repository.AnswerRepository;
import canard.intern.post.following.backend.repository.SurveyRepository;
import canard.intern.post.following.backend.repository.TraineeRepository;
import canard.intern.post.following.backend.service.AnswerService;

@Service
public class AnswerServiceJpa implements AnswerService{
	@Autowired
	AnswerRepository answerRepository;
	
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    TraineeRepository traineeRepository;
    
    @Autowired
    SurveyRepository surveyRepostiory;
	
	@Override
	public List<AnswerDto> getAll() {
		return answerRepository.findAll()
				.stream()
				.map((a)->modelMapper.map(a,AnswerDto.class))
				.toList();
	}

	@Override
	public Optional<AnswerDto> getById(int id) {
   	 return answerRepository.findById(id)
             .map((te)-> modelMapper.map(te, AnswerDto.class));
	}

	@Override
	public AnswerDto create(AnswerDto answerDto) {
        Answer answerDb;
        try {
            answerDb = answerRepository.save(modelMapper.map(answerDto, Answer.class));
        }catch(Exception e){
            throw (new UpdateException("answer couldn't be saved",e));
        }
        return modelMapper.map(answerDb, AnswerDto.class);
	}

}
