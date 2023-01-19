package canard.intern.post.following.backend.service;

import canard.intern.post.following.backend.dto.SurveyDetailDto;
import canard.intern.post.following.backend.dto.SurveyDto;
import canard.intern.post.following.backend.error.UpdateException;

import java.util.List;
import java.util.Optional;

public interface SurveyService {

    /**
     * get all surveys
     * @return surveys
     */
    List<SurveyDto> getAll();

    /**
     * get a survey by its id if exists
     * @param id id of survey
     * @return optional with survey found
     * or optional empty if not exists
     */
    //Optional<SurveyDetailDto> getById(int id);

    /**
     * create survey and generate an id
     * @param surveyDto survey to create (without id)
     * @return survey created with its id
     * @throws UpdateException if survey cannot be created
     */
    SurveyDto create(SurveyDto surveyDto);
}
