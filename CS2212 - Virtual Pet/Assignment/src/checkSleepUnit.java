import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class checkSleepUnit {

	@Test
	void test() {
		Pet testPet = new Pet("easy", 100, 100, 100, 100, 100, 100, 100, 100, 10, 10, 10, 10, 20, 20 ,20, 20, 5, 5, 5, 5, 5, 5, "Dog", 0);
		PetController controller = new PetController(testPet);
		testPet.setSleep(0);
		controller.checkSleep();
		assertEquals(true, testPet.isSleeping());
		fail("Not yet implemented");
	}

}
