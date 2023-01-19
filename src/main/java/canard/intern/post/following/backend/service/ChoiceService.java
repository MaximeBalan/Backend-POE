package canard.intern.post.following.backend.service;

import java.util.List;
import java.util.Optional;


import canard.intern.post.following.backend.dto.ChoiceDto;
import canard.intern.post.following.backend.dto.TraineeDetailDto;
import canard.intern.post.following.backend.error.UpdateException;

public interface ChoiceService {
	  /**
     * get all choices
     * @return choices
     */
    List<ChoiceDto> getAll();

    /**
     * get a choice by its id if exists
     * @param id id of choice
     * @return optional with choice found
     * or optional empty if not exists
     */
    Optional<ChoiceDto> getById(int id);

  

    /**
     * create choice and generate an id
     * @param choiceDto choice to create (without id)
     * @return choice created with its id
     * @throws UpdateException if choice cannot be created
     */
    ChoiceDto create(ChoiceDto choiceDto);

    /**
     * update a choice with this id if exists ;
     * replace all fields with fields from argument choiceDto
     * @param id id of choices to update
     * @param choiceDto new version of choice to update
     * @return optional with choice updated if exists
     * or optional empty if not found
     * @throws UpdateException if found but cannot be updated
     */
    Optional<ChoiceDto> update(int id, ChoiceDto choiceDto);

    /**
     * delete choice with this id if exists
     * @param id id of choice to delete
     * @return true if deleted, false if not found
     * @throws UpdateException if found and cannot be deleted
     */
    boolean delete(int id);

    /**
     *
     * @param idChoice
     * @param idQuestion
     * @return choice with details if choices and question exist
     * else optional empty
     */
    Optional<TraineeDetailDto> setQuestion(int idChoice, int idQuestion);
}
