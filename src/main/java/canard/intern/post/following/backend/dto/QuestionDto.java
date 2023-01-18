package canard.intern.post.following.backend.dto;

import canard.intern.post.following.backend.entity.Choice;
import canard.intern.post.following.backend.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionDto {

    private Integer id;
    private String title;
    private QuestionType questionType;

    public void get() {
    }
}