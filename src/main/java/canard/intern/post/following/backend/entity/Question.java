package canard.intern.post.following.backend.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import canard.intern.post.following.backend.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name="questions")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //pour gérer automatiquement les id
	private Integer id;
	
	@Column(length=50, nullable=false)
	private String title;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length=13)
	private QuestionType questionType;
	
	// @Transient //not ready yet
	@OneToMany
	@JoinColumn(name="question_id")
	private Set<Choice> choices = new HashSet<>();
}
