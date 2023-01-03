package canard.intern.post.following.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PoeDto {
    private Integer id;
    private String title;
    private String poeType;
    private LocalDate beginDate;
    private LocalDate endDate;
}