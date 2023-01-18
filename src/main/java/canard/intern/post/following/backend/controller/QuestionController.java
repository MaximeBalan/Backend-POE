package canard.intern.post.following.backend.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import canard.intern.post.following.backend.dto.QuestionDetailDto;
import canard.intern.post.following.backend.dto.QuestionDto;
import canard.intern.post.following.backend.service.QuestionService;
import canard.intern.post.following.backend.service.impl.QuestionServiceJpa;


@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    @Autowired
    private QuestionServiceJpa questionService;

    /**
     * GET /api/questions
     * @return all question
     */
    @GetMapping
    public List<QuestionDto> getAll(){return questionService.getAll();
    }

    @GetMapping("/{id}")
    public QuestionDetailDto getById(@PathVariable("id") int id){
        var optQuestionDto =  questionService.getById(id);
        if (optQuestionDto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("No question found with id <%d>", id));
        }
        return optQuestionDto.get();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionDto create(@Valid @RequestBody QuestionDto questionDto) {
        return questionService.create(questionDto);
    }

    @PutMapping("/{id}")
    public QuestionDto update(@PathVariable("id") int id, @Valid @RequestBody QuestionDto questionDto )
    {
        if (Objects.nonNull(questionDto.getId()) && (questionDto.getId() != id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Id <%d> from path does not match id <%d> from body",
                            id, questionDto.getId()));
            // NB:you can use also:  MessageFormat.format or StringBuilder
        }
        var optTraineeDto = questionService.update(id, questionDto);
        if(optTraineeDto.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("No question found with id <%d>",
                            id));
        }
        return optTraineeDto.get();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id){
        var deleted = questionService.delete(id);
        if(!deleted){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("No question found with id <%d>",
                            id));
        }
    }
}