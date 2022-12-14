package canard.intern.post.following.backend.service.impl;

import canard.intern.post.following.backend.dto.TraineeDto;
import canard.intern.post.following.backend.entity.Trainee;
import canard.intern.post.following.backend.enums.Gender;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ModelMapperTraineeTest {

    static ModelMapper modelMapper;

    @BeforeAll
    static void initMapper(){
        modelMapper = new ModelMapper();
    }

    @Test
    void mapTraineeEntityToDto(){
        int id = 12345;
        String lastname = "Bond";
        String firstname = "James";
        Gender gender = Gender.M;
        LocalDate birthdate = LocalDate.of(1950, 1, 6);
        String email = "james@bond.mi6";
        String phoneNumber = "+33612345678";
        var traineeEntity = Trainee.builder()
                .id(id)
                .lastname(lastname)
                .firstname(firstname)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
        var traineeDto = modelMapper.map(traineeEntity, TraineeDto.class);
        assertNotNull(traineeDto); //vérifie si l'objet n'est pas null
        assertEquals(TraineeDto.class, traineeDto.getClass()); //vérifie si l'objet est bien un TraineeDto
        //vérifie que toutes les données sont bonnes et correspondent
        assertAll(
                () -> assertEquals(id, traineeDto.getId(), "trainee dto id"),
                () -> assertEquals(lastname, traineeDto.getLastname(), "trainee dto lastname"),
                () -> assertEquals(firstname, traineeDto.getFirstname(), "trainee dto firstname"),
                () -> assertEquals(gender, traineeDto.getGender(), "trainee dto gender"),
                () -> assertEquals(email, traineeDto.getEmail(), "trainee dto email"),
                () -> assertEquals(phoneNumber, traineeDto.getPhoneNumber(), "trainee dto phoneNumber"),
                () -> assertEquals(birthdate, traineeDto.getBirthdate(), "trainee dto birthdate")
        );
    }
}
