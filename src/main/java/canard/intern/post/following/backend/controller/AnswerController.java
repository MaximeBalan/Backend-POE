package canard.intern.post.following.backend.controller;

import java.util.List;
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
import canard.intern.post.following.backend.dto.AnswerDto;
import canard.intern.post.following.backend.service.impl.AnswerServiceJpa;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {
	
	@Autowired
	private AnswerServiceJpa answerService;

    @GetMapping
    public List<AnswerDto> getAll(){return answerService.getAll();
    }

    @GetMapping("/{id}")
    public AnswerDto getById(@PathVariable("id") int id){
        var optAnswerDetailDto =  answerService.getById(id);
        if (optAnswerDetailDto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("No answer found with id <%d>", id));
        }
        return optAnswerDetailDto.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnswerDto create(@RequestBody AnswerDto answerDto) {
        return answerService.create(answerDto);
    }
}
