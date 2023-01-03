package canard.intern.post.following.backend.service;

import canard.intern.post.following.backend.entity.Trainee;
import canard.intern.post.following.backend.repository.StagiaireRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//classe singleton, on ne peut l'instancier qu'une seule fois
public class StagiaireService {

    //attributes
    private static StagiaireService instance;
    private StagiaireRepository repository = StagiaireRepository.getInstance(); //injection de dépendance

    //constructor
    private StagiaireService(){
        System.out.println("I'm the service constructor");
    }

    public static StagiaireService getInstance(){
        if(StagiaireService.instance == null){
            StagiaireService.instance = new StagiaireService();
        }
        return StagiaireService.instance;
    }

    public List<Trainee> findAll(){

        return repository.findAll();
    }

    public Trainee add(){
        Trainee stagiaire = new Trainee();
        stagiaire.setId(2);
        stagiaire.setLastname("Taladon");
        stagiaire.setFirstname("Delphine");
        stagiaire.setEmail("talarondelphine@sfr.fr");
        stagiaire.setBirthdate(LocalDate.of(1999,12,10));

        return repository.add(stagiaire);

    }
}

