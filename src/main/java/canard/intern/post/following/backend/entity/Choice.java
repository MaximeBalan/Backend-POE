package canard.intern.post.following.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

@Table(name="choices")
public class Choice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //pour gérer automatiquement les id
	private Integer id;
	
	@Column(length=50, nullable=false)
	private String name;
}