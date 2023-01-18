package canard.intern.post.following.backend.service;

import canard.intern.post.following.backend.dto.QuestionDetailDto;
import canard.intern.post.following.backend.dto.QuestionDto;
import canard.intern.post.following.backend.error.UpdateException;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    /**
     * get all questions
     * @return questions
     */
    List<QuestionDto> getAll();

    /**
     * get a question by its id if exists
     * @param id id of question
     * @return optional with question found
     * or optional empty if not exists
     */
    Optional<QuestionDetailDto> getById(int id);

    List<QuestionDetailDto> getByType(String type);


    List<QuestionDto> getByTitle(String title);


    /**
     * create question and generate an id
     * @param questionDto question to create (without id)
     * @return question created with its id
     * @throws UpdateException if question cannot be created
     */
    QuestionDto create(QuestionDto questionDto);

    /**
     * update a question with this id if exists ;
     * replace all fields with fields from argument questionDto
     * @param id id of question to update
     * @param questionDto new version of question to update
     * @return optional with question updated if exists
     * or optional empty if not found
     * @throws UpdateException if found but cannot be updated
     */
    Optional<QuestionDto> update(int id, QuestionDto questionDto);

    /**
     * find trainees of this questions
     * then questionPoe(null) for each one
     * delete question with this id if exists
     * @param id id of question to delete
     * @return true if deleted, false if not found
     * @throws UpdateException if found and cannot be deleted
     */
    boolean delete(int id);
}
