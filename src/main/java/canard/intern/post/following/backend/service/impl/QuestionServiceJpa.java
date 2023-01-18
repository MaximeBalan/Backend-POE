package canard.intern.post.following.backend.service.impl;

import canard.intern.post.following.backend.dto.*;
import canard.intern.post.following.backend.entity.Poe;
import canard.intern.post.following.backend.entity.Question;
import canard.intern.post.following.backend.error.UpdateException;
import canard.intern.post.following.backend.repository.ChoiceRepository;
import canard.intern.post.following.backend.repository.PoeRepository;
import canard.intern.post.following.backend.repository.QuestionRepository;
import canard.intern.post.following.backend.repository.TraineeRepository;
import canard.intern.post.following.backend.service.ChoiceService;
import canard.intern.post.following.backend.service.QuestionService;
import canard.intern.post.following.backend.service.TraineeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class QuestionServiceJpa implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ChoiceRepository choiceRepository;

    @Autowired
    private ChoiceService choiceService;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<QuestionDto> getAll() {
        return questionRepository.findAll()
                .stream()
                .map((q) ->modelMapper.map(q,QuestionDto.class))
                .toList();
    }

    @Override
    public Optional<QuestionDetailDto> getById(int id) {
        var optQuestion = questionRepository.findById(id);
        if(optQuestion.isPresent()){
            var choices = choiceRepository.findByQuestionId(id)
                    .stream()
                    .map(choiceEntity -> modelMapper.map(choiceEntity, ChoiceDto.class))
                    .toList();
            var questionDDto = modelMapper.map(optQuestion.get(), QuestionDetailDto.class);
            questionDDto.setChoices(choices);
            return Optional.of(questionDDto);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<QuestionDetailDto> getByType(String type) {
        return questionRepository.getByQuestionType(type)
                .stream()
                .map(questionEntity -> modelMapper.map(questionEntity, QuestionDetailDto.class))
                .toList();
    }

    @Override
    public List<QuestionDto> getByTitle(String title) {
        return questionRepository.findByTitle(title)
                .stream()
                .map((t)->modelMapper.map(title, QuestionDto.class))
                .toList();
    }

    @Override
    public QuestionDto create(QuestionDto questionDto) {
        Question questionDb;
        try {
            questionDb= questionRepository.save(modelMapper.map(questionDto, Question.class));
        }catch(Exception e){
            throw (new UpdateException("question couldn't be saved",e));
        }

        return modelMapper.map(questionDb, QuestionDto.class);
    }


    @Override
    public Optional<QuestionDto> update(int id, QuestionDto questionDto) {
        questionDto.setId(id);
        var optQuestionDb = questionRepository.findById(id);
        try{
            if(optQuestionDb.isPresent()){
                var questionDb = optQuestionDb.get();
                modelMapper.map(questionDto, questionDb);
                questionRepository.flush();
                return Optional.of(modelMapper.map(questionDb, QuestionDto.class));
            }else{
                return Optional.empty();
            }
        }catch (DataIntegrityViolationException e) {
            throw new UpdateException("question couldn't be updated", e);
        }
    }
    @Transactional
    @Override
    public boolean delete(int id) {
        try{
            if(questionRepository.findById(id).isPresent()){
                choiceRepository.findByQuestionId(id)
                        .stream()
                        .forEach((c)-> c.setChoice(null));
                questionRepository.flush();
                questionRepository.deleteById(id);
                return true;
            }else{
                return false;
            }
        }catch(DataIntegrityViolationException e){// autres problèmes
            throw (new UpdateException("Poe couldn't be deleted",e));
        }
    }
}
