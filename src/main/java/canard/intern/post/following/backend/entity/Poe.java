package canard.intern.post.following.backend.entity;

import canard.intern.post.following.backend.enums.Gender;
import canard.intern.post.following.backend.enums.PoeType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

//pour rédiger les tests
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//pour lombok
@Getter
@Setter
@Entity
//pour personnaliser les tables
@Table(name = "poe")
public class Poe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pour gérer automatiquement les id
    private Integer id;

    @Column(length = 150, nullable = false)
    private String title;

    @Column(name= "begin_date", nullable = false)
    private LocalDate beginDate;

    @Column(name= "end_date", nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name= "poe_type",length = 10, nullable = false)
     private PoeType poeType;

    //@OneToMany
    //private Set<Trainee> trainees;
}


