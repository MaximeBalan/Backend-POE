package canard.intern.post.following.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class PoeDetailDto extends PoeDto{

    private List<TraineeDto> trainees;

}
