
package canard.intern.post.following.backend.service.impl;

import canard.intern.post.following.backend.dto.ChoiceDetailDto;
import canard.intern.post.following.backend.dto.ChoiceDto;
import canard.intern.post.following.backend.dto.QuestionDto;
import canard.intern.post.following.backend.entity.Choice;
import canard.intern.post.following.backend.error.UpdateException;

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
    public Optional<ChoiceDetailDto> getById(int id) {
//        var optChoice = choiceRepository.findById(id);
//        if(optChoice.isPresent()){
//            var questions = questionRepository.findByChoiceId(id)
//                    .stream()
//                    .map(questionEntity -> modelMapper.map(questionEntity, QuestionDto.class))
//                    .toList();
//        }

        return Optional.empty();
    }


    @Override
    public List<ChoiceDto> getByName(String name) {

        return choiceRepository.findByName(name)
                .stream()
                .map((n)->modelMapper.map(name,ChoiceDto.class))
                .toList();
    }

    @Override
    public ChoiceDto create(ChoiceDto choiceDto) {
        Choice choiceDb;
        try{
            choiceDb = choiceRepository.save(modelMapper.map(choiceDto, Choice.class));
        }catch(Exception e){
            throw (new UpdateException("question couldn't be saved",e));
        }
        return modelMapper.map(choiceDb, ChoiceDto.class);
    }

    @Override
    public Optional<ChoiceDto> update(int id, ChoiceDto choiceDto) {
        return Optional.empty();
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
