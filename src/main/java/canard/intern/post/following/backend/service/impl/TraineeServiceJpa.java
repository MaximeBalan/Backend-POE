package canard.intern.post.following.backend.service.impl;

import canard.intern.post.following.backend.dto.TraineeDto;
import canard.intern.post.following.backend.entity.Trainee;
import canard.intern.post.following.backend.error.UpdateException;
import canard.intern.post.following.backend.repository.TraineeRepository;
import canard.intern.post.following.backend.service.TraineeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TraineeServiceJpa implements TraineeService {

    @Autowired
    private TraineeRepository traineeRepository;
    @Autowired
    private ModelMapper modelMapper;


    public List<TraineeDto> getAll() {
//        return traineeRepository.findAll().stream().map(
//                (t)->TraineeDto.builder()
//                        .id(t.getId())
//                        .lastname(t.getLastname())
//                        .email(t.getEmail())
//                        .firstname(t.getFirstname())
//                        .gender(t.getGender())
//                        .birthdate(t.getBirthdate())
//                        .phoneNumber(t.getPhoneNumber())
//                        .build()
//
//        ).toList();
        return traineeRepository.findAll().stream().map((t)->modelMapper.map(t,TraineeDto.class)).toList();
    }

    @Override
    public Optional<TraineeDto> getById(int id) {
        // on récup la un optional de Trainee


        return traineeRepository.findById(id)
                .map((te)-> modelMapper.map(te, TraineeDto.class));//transfo que si y'a kkchose dans la boite



//        if (opTrainee.isPresent()){
//            // On convertit le Trainee en traineeDto s'il est pas vide
//            var traineeDto= modelMapper.map(opTrainee.get(),TraineeDto.class);
//
//            return Optional.of(traineeDto);
//        }
//        else{
//            return Optional.empty();
//        }
    }

    @Override
    public Set<TraineeDto> getByLastnameContaining(String lastnamePartial) {

        return null;
    }

    @Override
    public TraineeDto create(TraineeDto traineeDto) {
        // transformer traineeDto en trainee (grace au modelMapper)
        // le sauver dans la base de donnée grace au traineeRepository.save
        // récupérer le trainee renvoyé par traineeRepository.save et le convertir en TraineeDto
        Trainee traineeDb;
        try {
            traineeDb= traineeRepository.save(modelMapper.map(traineeDto, Trainee.class));
        }catch(Exception e){
            throw (new UpdateException("trainee couldn't be saved",e));
        }

        return modelMapper.map(traineeDb, TraineeDto.class);
    }

    @Override
    public Optional<TraineeDto> update(int id, TraineeDto traineeDto) {
        // in case traineeDto has no id
        traineeDto.setId(id);
        try {
            return traineeRepository.findById(id)
                    .map(te -> {
                        // update in DB if found
                        // 1 - overwrite entity fields with dto fields
                        modelMapper.map(traineeDto, te);
                        // 2 - synchronize with DB
                        traineeRepository.flush(); // force SQL UPDATE here
                        // 3 - transform back modified entity in  DTO
                        return modelMapper.map(te, TraineeDto.class);
                    });
        } catch (DataIntegrityViolationException ex) {
            throw new UpdateException("Trainee cannot be saved", ex);
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            return traineeRepository.findById(id)
                    .map(te -> {
                        // delete in DB if found
                        traineeRepository.delete(te);
                        traineeRepository.flush(); // force SQL delete here
                        return true;
                    })
                    .orElse(false);
        } catch (DataIntegrityViolationException ex) {
            throw new UpdateException("Trainee cannot be saved", ex);
        }
    }
}
