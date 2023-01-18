package canard.intern.post.following.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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

}