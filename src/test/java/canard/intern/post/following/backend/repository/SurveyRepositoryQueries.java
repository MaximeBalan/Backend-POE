package canard.intern.post.following.backend.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE) //pour dire qu'on a configuré ce test pour aller sur la meme base que l'application
public class SurveyRepositoryQueries {

    @Autowired
    SurveyRepository surveyRepository;

    @Test
    void displayOneSurvey(){
        var id = 1;
        surveyRepository.findById(id)
                //si il existe
                .ifPresent(survey -> {
                    //on affiche les choix
                    System.out.println(survey);
                    //on récupère les questions
                    survey.getQuestions()
                            .forEach(question -> {
                                //on affiche les questions
                                System.out.println("\t- " + question);
                                //on récupère les choix
                                question.getChoices()
                                        .forEach(choice-> {
                                            //on affiche les choix
                                            System.out.println("\t\t* " + choice);
                                        });
                            });
                });
    }



}
