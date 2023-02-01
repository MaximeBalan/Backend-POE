package canard.intern.post.following.backend.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SurveyDto {
    private Integer id;
    
    @NotBlank
    private String title;
}
