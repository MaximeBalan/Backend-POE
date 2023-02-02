package canard.intern.post.following.backend.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class AnswerDto {
	
	private Integer id;
	
	@NotBlank
	private String value;
	
	private SurveyDto survey;
	
	private TraineeDto trainee; 

}
