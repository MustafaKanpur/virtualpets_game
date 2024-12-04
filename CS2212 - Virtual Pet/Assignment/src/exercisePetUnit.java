import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class exercisePetUnit {

	@Test
	void test() {
		Pet testPet = new Pet("easy", 100, 100, 100, 100, 100, 100, 100, 100, 10, 10, 10, 10, 20, 20 ,20, 20, 5, 5, 5, 5, 5, 5, "Dog", 0);
		PetController controller = new PetController(testPet);
		controller.exerciseWithPet();
		
		assertEquals(100, testPet.getHealth());
		assertEquals(90, testPet.getSleep());
		assertEquals(90, testPet.getSleep());
		fail("Not yet implemented");
	}

}
