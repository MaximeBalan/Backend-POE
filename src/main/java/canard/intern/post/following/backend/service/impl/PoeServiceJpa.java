package canard.intern.post.following.backend.service.impl;

import canard.intern.post.following.backend.dto.PoeDetailDto;
import canard.intern.post.following.backend.dto.PoeDto;
import canard.intern.post.following.backend.dto.TraineeDetailDto;
import canard.intern.post.following.backend.dto.TraineeDto;
import canard.intern.post.following.backend.entity.Poe;
import canard.intern.post.following.backend.enums.PoeType;
import canard.intern.post.following.backend.error.UpdateException;
import canard.intern.post.following.backend.repository.PoeRepository;
import canard.intern.post.following.backend.repository.TraineeRepository;
import canard.intern.post.following.backend.service.PoeService;
import canard.intern.post.following.backend.service.TraineeService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class PoeServiceJpa implements PoeService {

    @Autowired
    private PoeRepository poeRepository;

    @Autowired
    private TraineeRepository traineeRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private TraineeService traineeService;


    @Override
    public List<PoeDto> getAll() {
        return poeRepository.findAll()
                .stream()
                .map((t)->modelMapper.map(t,PoeDto.class))
                .toList();
    }

    @Override
    public Optional<PoeDetailDto> getById(int id) {
        var optPoe = poeRepository.findById(id);
        if (optPoe.isPresent()){
            var trainees = traineeRepository.findByPoeId(id)
                    .stream()
                    .map(traineeEntity -> modelMapper.map(traineeEntity, TraineeDto.class))
                    .toList();
            var poeDDto= modelMapper.map(optPoe.get(),PoeDetailDto.class);
            poeDDto.setTrainees(trainees);
            return Optional.of(poeDDto);
        }
        else{
            return Optional.empty();
        }
    }
    
    
    public List<PoeDetailDto> getByType(String type) {
    	return poeRepository.getByPoeType(type)
    			.stream()
    			.map(poeEntity -> modelMapper.map(poeEntity, PoeDetailDto.class))
    			.toList();
    }

        
    @Override
    public List<PoeDto> getByTitle(String title) {
        // TODO
        return List.of();
    }

    @Override
    public List<PoeDto> getByStartingYear(int year) {
        // TODO
        return List.of();
    }

    @Override
    public PoeDto create(PoeDto poeDto) {
        // transformer traineeDto en trainee (grace au modelMapper)
        // le sauver dans la base de donnée grace au traineeRepository.save
        // récupérer le trainee renvoyé par traineeRepository.save et le convertir en TraineeDto
        Poe poeDb;
        try {
            poeDb= poeRepository.save(modelMapper.map(poeDto, Poe.class));
        }catch(Exception e){
            throw (new UpdateException("poe couldn't be saved",e));
        }

        return modelMapper.map(poeDb, PoeDto.class);
    }

    @Override
    public Optional<PoeDto> update(int id, PoeDto poeDto) {
        poeDto.setId(id);
        var optPoeDb = poeRepository.findById(id);
        try {
            if (optPoeDb.isPresent()) {
                var poeDb = optPoeDb.get();
                modelMapper.map(poeDto, poeDb); // change traineeDb in hibernate cache
                poeRepository.flush(); // synchro et force l'update en sql
                return Optional.of(modelMapper.map(poeDb, PoeDto.class));
            } else {
                return Optional.empty();
            }
        } catch (DataIntegrityViolationException e) {
            throw new UpdateException("trainee couldn't be updated", e);
        }
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        try {
            if (poeRepository.findById(id).isPresent()) {
            	//find trainees of this poes
            	//then setPoe(null) for each one
            	traineeRepository.findByPoeId(id)
            			.stream()
            			.forEach((t)->t.setPoe(null));
            	poeRepository.flush(); //force la synchronisation avec la base de données
                poeRepository.deleteById(id);
                return true;
            } else {
                return false; 
            }
        }catch(DataIntegrityViolationException e){// autres problèmes
            throw (new UpdateException("Poe couldn't be deleted",e));
        }
    }

}