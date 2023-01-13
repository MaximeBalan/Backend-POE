package canard.intern.post.following.backend.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class QuestionTest {
	
	@Test
	void testAssociation() {
		var q = new Question();
		assertEquals(0, q.getChoices().size());
		Choice c1 = new Choice();
		var c5 = new Choice();
		
		System.out.println(q);
	}

}
