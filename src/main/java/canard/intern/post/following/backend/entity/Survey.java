package canard.intern.post.following.backend.entity;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table (name = "surveys")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @Column(length = 150, nullable = false)
    private String title;
    
    @ManyToMany
    @JoinTable( name = "survey_contains_question",
    joinColumns = @JoinColumn(name = "survey_id"), //FK to this class(Survey)
    inverseJoinColumns = @JoinColumn (name = "question_id")) //FK of the other class (Question)
    private Set<Question> questions = new HashSet<>();
}
