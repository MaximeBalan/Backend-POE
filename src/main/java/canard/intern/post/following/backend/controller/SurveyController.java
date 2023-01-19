package canard.intern.post.following.backend.controller;


import canard.intern.post.following.backend.dto.ChoiceDto;
import canard.intern.post.following.backend.dto.SurveyDetailDto;
import canard.intern.post.following.backend.dto.SurveyDto;
import canard.intern.post.following.backend.service.impl.ChoiceServiceJpa;
import canard.intern.post.following.backend.service.impl.SurveyServiceJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

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

//    @GetMapping("/{id}")
//    public SurveyDetailDto getById(@PathVariable("id") int id){
//        var optSurveyDetailDto =  surveyService.getById(id);
//        if (optSurveyDetailDto.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                    String.format("No survey found with id <%d>", id));
//        }
//        return optSurveyDetailDto.get();
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SurveyDto create(@Valid @RequestBody SurveyDto surveyDto) {
        return surveyService.create(surveyDto);
    }
}
