package canard.intern.post.following.backend.controller;

import canard.intern.post.following.backend.entity.Trainee;
import canard.intern.post.following.backend.enums.Gender;
import canard.intern.post.following.backend.service.StagiaireService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping ("/api/stagiaire") //http:localhost:8080/api/stagiaire
public class StagiaireController {

    //ça revient au même que de faire ça: (@Autowired)
    private StagiaireService service = StagiaireService.getInstance();
    @GetMapping
    public List<Trainee> findAll(){

        //comme on a fait un singleton, on accède qu'une seule fois au constructeur meme si plusieurs instances
        //StagiaireService service = StagiaireService.getInstance();
        //StagiaireService otherService = StagiaireService.getInstance();

        return service.findAll();
    }

    @GetMapping("/add")
    public Trainee add(){
        return service.add();
    }
}
