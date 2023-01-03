package canard.intern.post.following.backend.repository;

import canard.intern.post.following.backend.entity.Trainee;
import canard.intern.post.following.backend.service.StagiaireService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StagiaireRepository {
    //attribute
    private static StagiaireRepository instance;
    private List<Trainee> stagiaires = new ArrayList<>();

    //constructor
    private StagiaireRepository(){
        _feed();
    }

    //methode
    public static StagiaireRepository getInstance() {
        if (StagiaireRepository.instance == null) {
            StagiaireRepository.instance = new StagiaireRepository();
        }
        return StagiaireRepository.instance;
    }


    public List<Trainee> findAll(){
        return stagiaires;
    }

    public Trainee add(Trainee trainee){
        stagiaires.add(trainee);
        return trainee;
    }

    //créer un objet stagiaire et l'ajoute à la liste
    public void _feed(){

        //creation d'un stagiaire
        Trainee stagiaire = new Trainee();
        stagiaire.setLastname("Latte");
        stagiaire.setFirstname("Trudy");
        stagiaire.setId(1);
        stagiaire.setBirthdate(LocalDate.of(1989, 1, 15));
        stagiaire.setEmail("t.l@sfr.fr");
        //ajout à la liste
        stagiaires.add(stagiaire);
    }

}
