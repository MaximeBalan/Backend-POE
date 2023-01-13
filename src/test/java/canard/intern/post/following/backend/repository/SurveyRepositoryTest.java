package canard.intern.post.following.backend.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;



@DataJpaTest
public class SurveyRepositoryTest {
	
	@Autowired
	SurveyRepository surveyRepository;
	
    @Autowired
    //EntityManager entityManager;
    TestEntityManager entityManager;

	
	@Test
    void findById(){
        //given

    }
}
