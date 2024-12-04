import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class checkHungryUnit {

	@Test
	void test() {
		Pet testPet = new Pet("easy", 100, 100, 100, 100, 100, 100, 100, 100, 10, 10, 10, 10, 20, 20 ,20, 20, 5, 5, 5, 5, 5, 5, "Dog", 0);
		PetController controller = new PetController(testPet);
		testPet.setFullness(0);
		controller.checkHungry();
		assertEquals(true, testPet.isHungry());
		fail("Not yet implemented");
	}

}
