package canard.intern.post.following.backend.entity;

import canard.intern.post.following.backend.enums.Gender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

//use database par défaut qui s'appelle H2
@DataJpaTest
class TraineeTest {

    //ORM d'Hibernate
    @Autowired
    EntityManager entityManager;

    @Test
    void mapping_Ok_allRequiredFields() {
        // given
        String lastname = "Bond";
        String firstname = "James";
        Gender gender = Gender.M;
        LocalDate birthdate = LocalDate.of(1950, 1, 6);
        String email = "james@bond.mi6";
        String phoneNumber = "+33612345678";
        var trainee = Trainee.builder()
                .lastname(lastname)
                .firstname(firstname)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();

        //then
        //pour enregistrer en base de données H2, génère un INSERT
        entityManager.persist(trainee);
        //force syncho between Hibernate and database
        entityManager.flush(); //oblige la synchronisation

        //verify : ID has been generated
        Integer idGenerated = trainee.getId();
        assertNotNull(idGenerated);

        //verify: data has been inserted in database
        //to clear Hibernate cache
        entityManager.clear(); //pour perdre la mémoire
        var traineeRead = entityManager.find(Trainee.class, idGenerated); // SELECT
        assertNotNull(traineeRead);
        assertAll(
                () -> assertEquals(idGenerated, traineeRead.getId(), "trainee id"),
                () -> assertEquals(lastname, traineeRead.getLastname(), "trainee lastname"),
                () -> assertEquals(firstname, traineeRead.getFirstname(), "trainee firstname"),
                () -> assertEquals(gender, traineeRead.getGender(), "trainee gender"),
                () -> assertEquals(email, traineeRead.getEmail(), "trainee email"),
                () -> assertEquals(phoneNumber, traineeRead.getPhoneNumber(), "trainee phoneNumber"),
                () -> assertEquals(birthdate, traineeRead.getBirthdate(), "trainee birthdate")
        );

    }

    @ParameterizedTest
    @EnumSource(Gender.class)
    @NullSource
    void mapping_Ok_genderNullable(Gender gender) {
        // given
        String lastname = "Bond";
        String firstname = "James";
        LocalDate birthdate = LocalDate.of(1950, 1, 6);
        String email = "james@bond.mi6";
        String phoneNumber = "+33612345678";
        var trainee = Trainee.builder()
                .lastname(lastname)
                .firstname(firstname)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();

        //then
        //pour enregistrer en base de données H2, génère un INSERT
        entityManager.persist(trainee);
        //force syncho between Hibernate and database
        entityManager.flush(); //oblige la synchronisation
        System.out.println(trainee);

        //verify : ID has been generated
        Integer idGenerated = trainee.getId();
        assertNotNull(idGenerated);

        //verify: data has been inserted in database
        //to clear Hibernate cache
        entityManager.clear(); //pour perdre la mémoire
        var traineeRead = entityManager.find(Trainee.class, idGenerated); // SELECT
        assertNotNull(traineeRead);
        assertAll(
                () -> assertEquals(idGenerated, traineeRead.getId(), "trainee id"),
                () -> assertEquals(lastname, traineeRead.getLastname(), "trainee lastname"),
                () -> assertEquals(firstname, traineeRead.getFirstname(), "trainee firstname"),
                () -> assertEquals(gender, traineeRead.getGender(), "trainee gender"),
                () -> assertEquals(email, traineeRead.getEmail(), "trainee email"),
                () -> assertEquals(phoneNumber, traineeRead.getPhoneNumber(), "trainee phoneNumber"),
                () -> assertEquals(birthdate, traineeRead.getBirthdate(), "trainee birthdate")
        );

    }

    @ParameterizedTest
    @ValueSource(strings = {"B", "Bond", "lISuQFINNICtLwSnlaCuOZxMBCwbqWbGiQBXsaYAcWsyqyNRbK"})
    void mapping_OK_requiredLastname(String lastname) {
        // given
        String firstname = "James";
        LocalDate birthdate = LocalDate.of(1950, 1, 6);
        Gender gender = Gender.M;
        String email = "james@bond.mi6";
        String phoneNumber = "+33612345678";
        var trainee = Trainee.builder()
                .lastname(lastname)
                .firstname(firstname)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();

        //then
        //pour enregistrer en base de données H2, génère un INSERT
        entityManager.persist(trainee);
        //force syncho between Hibernate and database
        entityManager.flush(); //oblige la synchronisation
        System.out.println(trainee);

        //verify : ID has been generated
        Integer idGenerated = trainee.getId();
        assertNotNull(idGenerated);

        //verify: data has been inserted in database
        //to clear Hibernate cache
        entityManager.clear(); //pour perdre la mémoire
        var traineeRead = entityManager.find(Trainee.class, idGenerated); // SELECT
        assertNotNull(traineeRead);
        assertAll(
                () -> assertEquals(idGenerated, traineeRead.getId(), "trainee id"),
                () -> assertEquals(lastname, traineeRead.getLastname(), "trainee lastname"),
                () -> assertEquals(firstname, traineeRead.getFirstname(), "trainee firstname"),
                () -> assertEquals(gender, traineeRead.getGender(), "trainee gender"),
                () -> assertEquals(email, traineeRead.getEmail(), "trainee email"),
                () -> assertEquals(phoneNumber, traineeRead.getPhoneNumber(), "trainee phoneNumber"),
                () -> assertEquals(birthdate, traineeRead.getBirthdate(), "trainee birthdate")
        );

    }
    @ParameterizedTest
    @ValueSource(strings = {"", "lISuQFINNICtLwSnlaCuOZxMBCwbqWbGiQBXsaYAcWsyqyNRbKZ"})
    @NullSource
    void mapping_KO_requiredLastname(String lastname) {
        // given
        String firstname = "James";
        LocalDate birthdate = LocalDate.of(1950, 1, 6);
        Gender gender = Gender.M;
        String email = "james@bond.mi6";
        String phoneNumber = "+33612345678";
        var trainee = Trainee.builder()
                .lastname(lastname)
                .firstname(firstname)
                .gender(gender)
                .birthdate(birthdate)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();

        //then and verify
        assertThrows(PersistenceException.class, ()-> {
            //pour enregistrer en base de données H2, génère un INSERT
            entityManager.persist(trainee);
            //force syncho between Hibernate and database
            entityManager.flush(); //oblige la synchronisation
            //System.out.println(trainee);
        });
    }
    @ParameterizedTest
    @CsvSource({
            "Bond , James, M, 1950-01-12, bond@james.mi6, 0070070070",
            "Bond , Jane, F, 1970-01-12, bond@jane.mi6, 0070070071",
            "Neymar , Jean,, 1951-01-12, neymar@jean.org, 0070070072",
            "Spectre , Le, X, 1949-01-12, le.spectre@badguy.mi6, 0070070072",
    })
    void mapping_Ok_allRequiredFields(
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

        //then
        //pour enregistrer en base de données H2, génère un INSERT
        entityManager.persist(trainee);
        //force syncho between Hibernate and database
        entityManager.flush(); //oblige la synchronisation

        //verify : ID has been generated
        Integer idGenerated = trainee.getId();
        assertNotNull(idGenerated);

        //verify: data has been inserted in database
        //to clear Hibernate cache
        entityManager.clear(); //pour perdre la mémoire
        var traineeRead = entityManager.find(Trainee.class, idGenerated); // SELECT
        assertNotNull(traineeRead);
        assertAll(
                () -> assertEquals(idGenerated, traineeRead.getId(), "trainee id"),
                () -> assertEquals(lastname, traineeRead.getLastname(), "trainee lastname"),
                () -> assertEquals(firstname, traineeRead.getFirstname(), "trainee firstname"),
                () -> assertEquals(gender, traineeRead.getGender(), "trainee gender"),
                () -> assertEquals(email, traineeRead.getEmail(), "trainee email"),
                () -> assertEquals(phoneNumber, traineeRead.getPhoneNumber(), "trainee phoneNumber"),
                () -> assertEquals(birthdate, traineeRead.getBirthdate(), "trainee birthdate")
        );

    }
}
