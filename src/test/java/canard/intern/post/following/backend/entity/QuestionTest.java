package canard.intern.post.following.backend.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuestionTest {

	@Test
	void testAssociation() {
		Question q = new Question();
		assertEquals(0,q.getChoices().size());
		Choice c1 = new Choice(); 
		q.getChoices().add(c1);
		System.out.println(q);
		
	}

}
