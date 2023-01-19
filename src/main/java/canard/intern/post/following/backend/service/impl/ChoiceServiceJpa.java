
package canard.intern.post.following.backend.service.impl;

import canard.intern.post.following.backend.dto.ChoiceDetailDto;
import canard.intern.post.following.backend.dto.ChoiceDto;
import canard.intern.post.following.backend.dto.PoeDetailDto;
import canard.intern.post.following.backend.dto.QuestionDetailDto;
import canard.intern.post.following.backend.dto.QuestionDto;
import canard.intern.post.following.backend.dto.TraineeDetailDto;
import canard.intern.post.following.backend.dto.TraineeDto;
import canard.intern.post.following.backend.entity.Choice;
import canard.intern.post.following.backend.error.UpdateException;

import canard.intern.post.following.backend.repository.ChoiceRepository;
import canard.intern.post.following.backend.repository.QuestionRepository;
import canard.intern.post.following.backend.service.ChoiceService;
import canard.intern.post.following.backend.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    	 return choiceRepository.findById(id)
                 .map((te)-> modelMapper.map(te, ChoiceDto.class));

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
            throw (new UpdateException("choice couldn't be saved",e));
        }
        return modelMapper.map(choiceDb, ChoiceDto.class);
    }

    @Override
    public Optional<ChoiceDto> update(int id, ChoiceDto choiceDto) {
    	choiceDto.setId(id);
        var optChoiceDb = choiceRepository.findById(id);
        try{
            if(optChoiceDb.isPresent()){
                var choiceDb = optChoiceDb.get();
                modelMapper.map(choiceDto, choiceDb);
                choiceRepository.flush();
                return Optional.of(modelMapper.map(choiceDb, ChoiceDto.class));
            }else{
                return Optional.empty();
            }
        }catch (DataIntegrityViolationException e) {
            throw new UpdateException("choice couldn't be updated", e);
        }
    }

    @Transactional
    @Override
    public boolean delete(int id) {
    	 try{
             if(choiceRepository.findById(id).isPresent()){
            	 choiceRepository.flush();
            	 choiceRepository.deleteById(id);
                 return true;
             }else{
                 return false;
             }
         }catch(DataIntegrityViolationException e){// autres problèmes
             throw (new UpdateException("Choice couldn't be deleted",e));
         }

     }
    
}
