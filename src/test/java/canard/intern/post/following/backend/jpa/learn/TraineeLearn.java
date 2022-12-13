package canard.intern.post.following.backend.jpa.learn;

import canard.intern.post.following.backend.entity.Trainee;
import canard.intern.post.following.backend.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

//use database par défaut qui s'appelle H2
@DataJpaTest
public class TraineeLearn {

    //ORM d'Hibernate
    @Autowired
    EntityManager entityManager;

    @Test
    void saveAndRead(){
        Trainee trainee = Trainee.builder()
                .lastname("Bond")
                .firstname("James")
                .gender(Gender.M)
                .birthdate(LocalDate.of(1950,1,6))
                .email("james@bond.mi6")
                .phoneNumber("+33612345678")
                .build();

        //pour enregistrer en base de données H2, génère un INSERT
        entityManager.persist(trainee);
        //force syncho between Hibernate and database
        entityManager.flush(); //oblige la synchronisation
        System.out.println(trainee);
        int idGenerated = trainee.getId();
        //to clear Hibernate cache
        entityManager.clear(); //pour perdre la mémoire

        var traineeRead = entityManager.find(Trainee.class, idGenerated); // SELECT
        System.out.println(traineeRead);
    }
}
