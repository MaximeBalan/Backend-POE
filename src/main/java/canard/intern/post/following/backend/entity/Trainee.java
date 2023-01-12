package canard.intern.post.following.backend.entity;

import canard.intern.post.following.backend.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
//pour rédiger les tests
@ToString(exclude = "poe")
@Builder
@NoArgsConstructor
@AllArgsConstructor
//pour lombok
@Getter @Setter
@Entity
//pour personnaliser les tables
@Table(name = "trainees")
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pour gérer automatiquement les id
    private Integer id;

    @Column(length = 50, nullable = false)
    private String lastname;

    @Column(length = 50, nullable = false)
    private String firstname;

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(length = 15)
    private String phoneNumber;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    //UML
    //@Transient // do not persist this attribute
    @ManyToOne//on va vers une poe au max
        //(fetch = FetchType.LAZY) // on demand (default Eager i.e. always)
    // @JoinColumn(name="ip_poe") // customize Foreign Key column
    private Poe poe; // référence objet


}
