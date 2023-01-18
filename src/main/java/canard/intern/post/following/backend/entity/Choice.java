package canard.intern.post.following.backend.entity;

import javax.persistence.*;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table (name = "choices")
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 150, nullable = false)
    private String name;

    @ManyToOne
    private Choice choice;
}

