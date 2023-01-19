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

//    @Override
//    public Optional<SurveyDetailDto> getById(int id) {
//        var optSurvey = surveyRepository.findById(id);
//        if(optSurvey.isPresent()){
//            var questions = questionRepository.findBySurveyId(id)
//                    .stream()
//                    .map(questionEntity -> modelMapper.map(questionEntity, QuestionDto.class))
//                    .toList();
//            var surveyDDto = modelMapper.map(optSurvey.get(), SurveyDetailDto.class);
//            surveyDDto.setQuestions(questions);
//            return Optional.of(surveyDDto);
//        } else {
//            return Optional.empty();
//        }
//    }

    @Override
    public SurveyDto create(SurveyDto surveyDto) {
        Survey surveyDb;
        try {
            surveyDb= surveyRepository.save(modelMapper.map(surveyDto, Survey.class));
        }catch(Exception e){
            throw (new UpdateException("survey couldn't be saved",e));
        }
        return modelMapper.map(surveyDb, SurveyDto.class);
    }

}
