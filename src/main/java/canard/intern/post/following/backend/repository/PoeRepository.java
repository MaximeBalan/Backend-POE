package canard.intern.post.following.backend.repository;

import canard.intern.post.following.backend.dto.IPoeTypeCountPoeDto;
import canard.intern.post.following.backend.dto.PoeTypeCountPoeDto;
import canard.intern.post.following.backend.entity.Poe;
import canard.intern.post.following.backend.enums.PoeType;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PoeRepository extends JpaRepository<Poe,Integer> {

    List<Poe> findByTitleIgnoreCaseOrderByBeginDate(String title);

    List<Poe> findByTitle(String title);
    
    @Query("SELECT p FROM Poe p WHERE p.poe_type = :type")
    Optional<Poe> findByPoeType(PoeType type);

    List<Poe> findByBeginDateBetween(LocalDate date1, LocalDate date2);
    List<Poe> findByBeginDateBetween(LocalDate date1, LocalDate date2, Sort sort);

    @Query("SELECT p FROM Poe p WHERE EXTRACT(YEAR FROM p.beginDate) = :year ORDER BY p.beginDate") //:year pour le param int year de la fonction
    List<Poe>findByBeginDateInYear(int year);

    @Query("SELECT p.type, COUNT(p) as countPoe FROM Poe p GROUP BY p.type")
    List<PoeTypeCountPoeDto> countPoeByPoeType();

    @Query("SELECT p.type as type, COUNT(p) as countPoe" +
            " FROM Poe p GROUP BY p.type")
    List<IPoeTypeCountPoeDto> countPoeByPoeType2();

    // NB: RIGHT JOIN here because association is unidirectional
    // in the Java Model: Trainee => Poe
    @Query("SELECT p.id as id, p.title as title, " +
            "   p.beginDate as beginDate, p.type as poe_type, " +
            "   COUNT(t.id) as traineeCount " +
            "FROM Trainee t RIGHT OUTER JOIN t.poe p " +
            "GROUP BY p " +
            "ORDER BY traineeCount")
    List<Tuple> countTraineesByPoe();

    


}