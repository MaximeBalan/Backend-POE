package canard.intern.post.following.backend.repository.play;

import canard.intern.post.following.backend.entity.Poe;
import canard.intern.post.following.backend.repository.PoeRepository;
import canard.intern.post.following.backend.repository.SurveyRepository;
import canard.intern.post.following.backend.repository.TraineeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaRepositoryQueries {

    @Autowired
    private TraineeRepository traineeRepository;

    @Autowired
    private PoeRepository poeRepository;
    
    @Autowired
    private SurveyRepository surveyRepository;

    private static void displayCollection(Collection<?> collection) {
        for (var e:collection) {
            System.out.println("\t- " + e);
        }
    }

    @Test
    void traineesByLastnamePartialIgnoreCase() {
        String partialName = "neym";
        var trainees = traineeRepository.findByLastnameIgnoreCase(partialName);
        displayCollection(trainees);
    }

    @Test
    void traineesByLastnamePartial() {
        String partialName = "Neym";
        var traineeList = traineeRepository.findByLastnameIgnoreCase(partialName);
        for(var trainee: traineeList){
            System.out.println("\t-" + trainee);
        }
    }
    @Test
    void traineesByPoeTitleIgnoreCase() {
        String title = "Consultant";
        var trainees = traineeRepository.findByPoeTitleIgnoreCase(title);
        displayCollection(trainees);
    }

    @Test
    void  poeByTitle() {
        String title = "Java Fullstack";
        List<Poe> poeList = poeRepository.findByTitle(title);
        for (var poe: poeList) {
            System.out.println("\t- " + poe);
        }
    }

    @Test
    void poeByTitleIgnoreCase() {
        String title = "java fullstack";
        var poe = poeRepository.findByTitleIgnoreCaseOrderByBeginDate(title);
        displayCollection(poe);
    }
/*
    @Test
    void poeByType() {
        String poeType = "POEC";
        var poe = poeRepository.findByPoeType(poeType);
        displayCollection(poe);
    }*/

    @Test
    void poeStartingYear(){
        int year = 2022;
        var poe = poeRepository.findByBeginDateBetween(
                LocalDate.of(year, 1, 1),
                LocalDate.of(year, 12, 1)
        );
        displayCollection(poe);
    }

    @Test
    void poeStartingYear_JpqlQuery(){
        int year = 2022;
        var poe = poeRepository.findByBeginDateInYear(year);
        displayCollection(poe);
    }

    @Test
    void poeStartingYearSorted() {
        int year = 2022;
        var poe = poeRepository.findByBeginDateBetween(
                LocalDate.of(year, 1, 1),
                LocalDate.of(year, 12, 1),
                Sort.by("beginDate")
        );
        displayCollection(poe);
    }

    // Sort
    @Test
    void poeSorted(){
        var poeSortedByDateDesc = poeRepository.findAll(
                Sort.by("beginDate").descending()
        );
        displayCollection(poeSortedByDateDesc);

        var poeSortedByTitleDate = poeRepository.findAll(
//                Sort.by("title")
//                        .and(Sort.by("beginDate"))
                Sort.by("title", "beginDate")
        );
        displayCollection(poeSortedByTitleDate);
    }
    
}