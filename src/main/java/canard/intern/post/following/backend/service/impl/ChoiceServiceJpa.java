
package canard.intern.post.following.backend.service.impl;

import canard.intern.post.following.backend.dto.ChoiceDto;

import canard.intern.post.following.backend.dto.TraineeDetailDto;
import canard.intern.post.following.backend.repository.ChoiceRepository;
import canard.intern.post.following.backend.repository.QuestionRepository;
import canard.intern.post.following.backend.service.ChoiceService;
import canard.intern.post.following.backend.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
	@Service
	public class ChoiceServiceJpa implements ChoiceService {

	    @Autowired
	    private ChoiceRepository choiceRepository;

	    @Autowired
	    private QuestionRepository questionRepository;

	    @Autowired
	    private ModelMapper modelMapper;

	    @Autowired
	    private QuestionService questionService;

	    @Override
	    public List<ChoiceDto> getAll() {
	        return choiceRepository.findAll()
	                .stream()
	                .map((c)-> modelMapper.map(c, ChoiceDto.class))
	                .toList();
	        
	    }

		@Override
		public Optional<ChoiceDto> getById(int id) {
			// TODO Auto-generated method stub
			return Optional.empty();
		}

		@Override
		public ChoiceDto create(ChoiceDto choiceDto) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Optional<ChoiceDto> update(int id, ChoiceDto choiceDto) {
			// TODO Auto-generated method stub
			return Optional.empty();
		}

		@Override
		public boolean delete(int id) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Optional<TraineeDetailDto> setQuestion(int idChoice, int idQuestion) {
			// TODO Auto-generated method stub
			return Optional.empty();
		}

	
	}