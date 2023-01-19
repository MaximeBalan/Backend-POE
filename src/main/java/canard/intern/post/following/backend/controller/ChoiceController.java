package canard.intern.post.following.backend.controller;

import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import canard.intern.post.following.backend.dto.ChoiceDto;
import canard.intern.post.following.backend.service.impl.ChoiceServiceJpa;


@RestController
@RequestMapping("/api/choices")
public class ChoiceController {
	
	 @Autowired
	    private ChoiceServiceJpa choiceService;
	 
	 /**
	     * GET /api/questions
	     * @return all question
	     */
	    @GetMapping
	    public List<ChoiceDto> getAll(){return choiceService.getAll();
	    }

	    @GetMapping("/{id}")
	    public ChoiceDto getById(@PathVariable("id") int id){
	        var optchoiceDto =  choiceService.getById(id);
	        if (optchoiceDto.isEmpty()) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
	                    String.format("No choice found with id <%d>", id));
	        }
	        return optchoiceDto.get();
	    }


	    @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    public ChoiceDto create(@Valid @RequestBody ChoiceDto choiceDto) {
	        return choiceService.create(choiceDto);
	    }

	    @PutMapping("/{id}")
	    public ChoiceDto update(@PathVariable("id") int id, @Valid @RequestBody ChoiceDto choiceDto )
	    {
	        if (Objects.nonNull(choiceDto.getId()) && (choiceDto.getId() != id)) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
	                    String.format("Id <%d> from path does not match id <%d> from body",
	                            id, choiceDto.getId()));
	            // NB:you can use also:  MessageFormat.format or StringBuilder
	        }
	        var optChoiceDto = choiceService.update(id, choiceDto);
	        if(optChoiceDto.isEmpty()){
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
	                    String.format("No choice found with id <%d>",
	                            id));
	        }
	        return optChoiceDto.get();
	    }

	    @DeleteMapping("/{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void delete(@PathVariable("id") int id){
	        var deleted = choiceService.delete(id);
	        if(!deleted){
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
	                    String.format("No choice found with id <%d>",
	                            id));
	        }
	    }

}
