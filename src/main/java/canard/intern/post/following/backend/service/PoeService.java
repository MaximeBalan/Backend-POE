package canard.intern.post.following.backend.service;

import canard.intern.post.following.backend.dto.PoeDto;
import canard.intern.post.following.backend.dto.TraineeDto;
import canard.intern.post.following.backend.error.UpdateException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PoeService {
    /**
     * get all poes
     * @return poes
     */
    List<PoeDto> getAll();

    /**
     * get a poe by its id if exists
     * @param id id of poe
     * @return optional with poe found
     * or optional empty if not exists
     */
    Optional<PoeDto> getById(int id);


    /**
     * create poe and generate an id
     * @param poeDto poe to create (without id)
     * @return poe created with its id
     * @throws UpdateException if poe cannot be created
     */
    PoeDto create(PoeDto poeDto);

    /**
     * update a poe with this id if exists ;
     * replace all fields with fields from argument poeDto
     * @param id id of poe to update
     * @param poeDto new version of poe to update
     * @return optional with poe updated if exists
     * or optional empty if not found
     * @throws UpdateException if found but cannot be updated
     */
    Optional<PoeDto> update(int id, PoeDto poeDto);

    /**
     * delete poe with this id if exists
     * @param id id of poe to delete
     * @return true if deleted, false if not found
     * @throws UpdateException if found and cannot be deleted
     */
    boolean delete(int id);

}
