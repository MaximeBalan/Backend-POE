package canard.intern.post.following.backend.service.impl;



import canard.intern.post.following.backend.dto.*;
import canard.intern.post.following.backend.entity.Question;
import canard.intern.post.following.backend.entity.Survey;
import canard.intern.post.following.backend.error.UpdateException;
import canard.intern.post.following.backend.repository.QuestionRepository;
import canard.intern.post.following.backend.repository.SurveyRepository;
import canard.intern.post.following.backend.service.SurveyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyServiceJpa implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private QuestionRepository questionRepository;

    // choice repo?

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SurveyDto> getAll() {
        return surveyRepository.findAll()
                .stream()
                .map((q) ->modelMapper.map(q,SurveyDto.class))
                .toList();
    }

    @Override
    public Optional<SurveyDetailDto> getById(int id) {
        var optSurvey = surveyRepository.findById(id);
        if(optSurvey.isPresent()){
//            var questions = questionRepository.findBySurveyId(id)
//                    .stream()
//                    .map(questionEntity -> modelMapper.map(questionEntity, QuestionDto.class))
//                    .toList();
            var surveyDDto = modelMapper.map(optSurvey.get(), SurveyDetailDto.class);
//            surveyDDto.setQuestions(questions);
            return Optional.of(surveyDDto);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public SurveyDetailDto create(SurveyDetailDto surveyDetailDto) {
        try {
        	Survey surveyDb= surveyRepository.save(modelMapper.map(surveyDetailDto, Survey.class));
            return modelMapper.map(surveyDb, SurveyDetailDto.class);
        }catch(Exception e){
            throw (new UpdateException("survey couldn't be saved",e));
        }
    
    }

	@Override
	public List<SurveyDto> getByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SurveyDto> update(int id, SurveyDto surveyDto) {
        surveyDto.setId(id);
        var optSurveyDb = surveyRepository.findById(id);
        try {
            if (optSurveyDb.isPresent()) {
                var surveyDb = optSurveyDb.get();
                modelMapper.map(surveyDto, surveyDb); // change traineeDb in hibernate cache
                surveyRepository.flush(); // synchro et force l'update en sql
                return Optional.of(modelMapper.map(surveyDb, SurveyDto.class));
            } else {
                return Optional.empty();
            }
        } catch (DataIntegrityViolationException e) {
            throw new UpdateException("survey couldn't be updated", e);
        }
    }

	@Override
	public boolean delete(int id) {
        try {
            return surveyRepository.findById(id)
                    .map(te -> {
                        // delete in DB if found
                    	surveyRepository.delete(te);
                    	surveyRepository.flush(); // force SQL delete here
                        return true;
                    })
                    .orElse(false);
        } catch (DataIntegrityViolationException ex) {
            throw new UpdateException("Survey cannot be saved", ex);
        }
    }

}
