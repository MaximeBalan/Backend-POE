package canard.intern.post.following.backend.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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

@Table(name="survey")
public class Survey {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY) //pour gérer automatiquement les id
		private Integer id;
		
		@Column(length=50, nullable=false)
		private String title;
		
		@ManyToMany
		@JoinTable(name="survey_contrains_question",
		joinColumns= @JoinColumn(name= "survey_id"),
		inverseJoinColumns= @JoinColumn(name = "question_id"))
		private Set<Question> questions= new HashSet<>();
}
