import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class vetUnit {

	@Test
	void test() {
		Pet testPet = new Pet("easy", 80, 100, 100, 100, 100, 100, 100, 100, 10, 10, 10, 10, 20, 20 ,20, 20, 5, 5, 5, 5, 5, 5, "Dog", 0);
		PetController controller = new PetController(testPet);
		controller.petToVet();
		
		assertEquals(100, testPet.getHealth());
		fail("Not yet implemented");
	}

}
