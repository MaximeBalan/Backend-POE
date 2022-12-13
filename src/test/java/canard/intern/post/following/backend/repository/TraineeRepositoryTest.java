package canard.intern.post.following.backend.repository;

import canard.intern.post.following.backend.entity.Trainee;
import canard.intern.post.following.backend.enums.Gender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class TraineeRepositoryTest {

    @Autowired
    TraineeRepository traineeRepository;

    // usefull wrapper og EntityManager only for test
    @Autowired
    //EntityManager entityManager;
    TestEntityManager entityManager;

    //@Rollback(value = false) => pour voir si les données enregistrées sont là
    @ParameterizedTest
    @CsvSource({
            "Bond , James, M, 1950-01-12, bond@james.mi6, 0070070070",
            "Bond , Jane, F, 1970-01-12, bond@jane.mi6, 0070070071",
            "Neymar , Jean,, 1951-01-12, neymar@jean.org, 0070070072",
            "Spectre , Le, X, 1949-01-12, le.spectre@badguy.mi6, 0070070072",
    })
    void save_Ok_allRequiredFields_Csv(
            String lastname,
            String firstname,
            Gender gender,
            LocalDate birthdate,
            String email,
            String phoneNumber
    ) {
        // given
        var trainee = Trainee.builder()
                .lastname(lastname)
                .firstname(firstname)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
        //to save
        //traineeRepository.save(trainee);
        traineeRepository.saveAndFlush(trainee);

        //verify 1: Id has been generated
        assertNotNull(trainee.getId());

        //optional: verify 2: read data from db to check if data has been inserted
    }

    @Test
    void findAll() {
        //given: save data in database
        var traineesDatabase = List.of(
                Trainee.builder()
                        .lastname("Bond")
                        .firstname("James")
                        .gender(Gender.M)
                        .birthdate(LocalDate.of(1950,1,1))
                        .email("bond@james.ord")
                        .phoneNumber("++37007007")
                        .build(),
                Trainee.builder()
                        .lastname("Bond")
                        .firstname("Jane")
                        .gender(Gender.F)
                        .birthdate(LocalDate.of(1955,1,1))
                        .email("bond@jane.ord")
                        .phoneNumber("++37007007132")
                        .build(),
                Trainee.builder()
                        .lastname("Spectre")
                        .firstname("Le")
                        .gender(Gender.X)
                        .birthdate(LocalDate.of(1950,3,3))
                        .email("le.spectre@badguy.ord")
                        .phoneNumber("++37007007147")
                        .build()
                );
        traineesDatabase.forEach(t->entityManager.persist(t));
        entityManager.flush();
        entityManager.clear(); //empty hibernate cache

        //when: read all trainees from database
        var traineesRead = traineeRepository.findAll();
        System.out.println(traineesRead);

        //verify: all data has been read (size and content)
        assertEquals(traineesDatabase.size(), traineesRead.size());

        //TODO: check content
    }

    @Test
    void findById_present(){
        //given

        var traineeDatabase =  Trainee.builder()
                .lastname("Bond")
                .firstname("James")
                .gender(Gender.M)
                .birthdate(LocalDate.of(1950,1,1))
                .email("bond@james.ord")
                .phoneNumber("++37007007")
                .build();
        int id = entityManager.persistAndGetId(traineeDatabase, Integer.class);
        entityManager.flush();
        entityManager.clear(); // empty hibernate cache

        //when
        var optTrainee = traineeRepository.findById(id);

        //verify
        assertTrue(optTrainee.isPresent(), "trainee is present");
    }
    @Test
    void findById_absent(){
        //given
        int id = 12345;

        //when
        var optTrainee = traineeRepository.findById(id);

        //verify
        assertTrue(optTrainee.isEmpty(), "trainee is absent");
    }
}