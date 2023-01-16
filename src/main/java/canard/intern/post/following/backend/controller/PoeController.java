package canard.intern.post.following.backend.controller;
import canard.intern.post.following.backend.dto.PoeDetailDto;
import canard.intern.post.following.backend.dto.PoeDto;
import canard.intern.post.following.backend.enums.PoeType;
import canard.intern.post.following.backend.service.PoeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/poe")
public class PoeController {
    @Autowired
    private PoeService poeService;

    /**
     * GET /api/poe
     * @return all poe
     */
    @GetMapping
    public List<PoeDto> getAll(){
        return poeService.getAll();
    }

    /**
     * GET /api/poe/{id}
     *
     * Example: in order to get poe of id 3, call
     *    GET /api/poe/3
     *
     * @param id id of poe to get
     * @return poe with this id if found
     */
    @GetMapping("/{id}")
    public PoeDetailDto getById(@PathVariable("id") int id){
        var optPoeDto =  poeService.getById(id);
        if (optPoeDto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("No poe found with id <%d>", id));
        }
        return optPoeDto.get();
    }
    
    @GetMapping("/{poeType}")
    public PoeDetailDto getByPoeType(@PathVariable("poeType") PoeType poeType){
        var optPoeDto =  poeService.getByType(poeType);
        if (optPoeDto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("No poe type found with type <%d>", poeType));
        }
        return optPoeDto.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PoeDto create(@Valid @RequestBody PoeDto poeDto) {
        return poeService.create(poeDto);

    }

    @PutMapping("/{id}")
    public PoeDto update(@PathVariable("id") int id, @Valid @RequestBody PoeDto poeDto){
        var optPoeDto =  poeService.update(id, poeDto);
        if (Objects.nonNull(poeDto.getId()) && (poeDto.getId() != id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Id <%d> from path does not match id <%d> from body", id, poeDto.getId()));
        }if (optPoeDto.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No trainee found with id  <%d> ", id));
        }
        return optPoeDto.get();
    }

    //NB: other choice, return Dto removed if found
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id){
        var optPoeDto = poeService.delete(id);
        if(!optPoeDto){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No trainee found with id  <%d> ", id));
        }
    }
}