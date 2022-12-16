package canard.intern.post.following.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TraineeDetailDto extends TraineeDto{

    //même nom que l'attribute dans Trainee.java @ManyToOne
    private PoeDto poe;
}
