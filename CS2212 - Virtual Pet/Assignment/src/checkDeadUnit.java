import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class checkDeadUnit {

	@Test
	void test() {
		Pet testPet = new Pet("easy", 100, 100, 100, 100, 100, 100, 100, 100, 10, 10, 10, 10, 20, 20 ,20, 20, 5, 5, 5, 5, 5, 5, "Dog", 0);
		PetController controller = new PetController(testPet);
		testPet.setHealth(0);
		controller.checkDead();
		assertEquals(true, testPet.isDead());
		fail("Not yet implemented");
	}

}
