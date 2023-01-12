package canard.intern.post.following.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import canard.intern.post.following.backend.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity

public class Question {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @Column(nullable = false, length = 50)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 13)
    private QuestionType questionType;
}
