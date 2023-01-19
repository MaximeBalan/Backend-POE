package canard.intern.post.following.backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class QuestionDetailDto extends QuestionDto {
	private List<ChoiceDto> choices;

}
