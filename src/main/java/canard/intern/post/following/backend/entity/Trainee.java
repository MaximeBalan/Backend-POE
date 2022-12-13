package canard.intern.post.following.backend.entity;

import canard.intern.post.following.backend.enums.Gender;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pour gérer automatiquement les id
    private Integer id;

    private String lastname;

    private String firstname;

    private Gender gender;

    private LocalDate birthdate;

    private String phoneNumber;

    private String email;
}
