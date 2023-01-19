package canard.intern.post.following.backend.controller;

import canard.intern.post.following.backend.dto.SurveyDetailDto;
import canard.intern.post.following.backend.dto.SurveyDto;
import canard.intern.post.following.backend.service.impl.SurveyServiceJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

    @Autowired
    private SurveyServiceJpa surveyService;

    /**
     * GET /api/surveys
     * @return all survey
     */
    @GetMapping
    public List<SurveyDto> getAll(){return surveyService.getAll();
    }

    @GetMapping("/{id}")
    public SurveyDetailDto getById(@PathVariable("id") int id){
        var optSurveyDetailDto =  surveyService.getById(id);
        if (optSurveyDetailDto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("No survey found with id <%d>", id));
        }
        return optSurveyDetailDto.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SurveyDto create(@Valid @RequestBody SurveyDto surveyDto) {
        return surveyService.create(surveyDto);
    }
    
    @PutMapping("/{id}")
    public SurveyDto update(@PathVariable("id") int id, @Valid @RequestBody SurveyDto surveyDto){
        var optSurveyDto =  surveyService.update(id, surveyDto);
        if (Objects.nonNull(surveyDto.getId()) && (surveyDto.getId() != id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Id <%d> from path does not match id <%d> from body", id, surveyDto.getId()));
        }if (optSurveyDto.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No Survey found with id  <%d> ", id));
        }
        return optSurveyDto.get();
    }
    
    //NB: other choice, return Dto removed if found
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id){
        var optSurveyDto = surveyService.delete(id);
        if(!optSurveyDto){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No Survey found with id  <%d> ", id));
        }
    }
}
