package petManagement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import petTypeManagement.PetType;
import userManagement.User;

class PetServiceTest {
	@Mock
	PetRepository petRepoMock;

	@InjectMocks
	PetService petService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void getPetsTest() {
		String username = "Varun";
		User user = new User(username, "salt", 0);
		PetType petype = new PetType(90, "Cat", "Siamese Cat", "Common");
		PetType petype2 = new PetType(91, "Dog", "Border Collie", "Rare");

		Pet pet = new Pet(1, "Tibbles", petype, user);
		Pet pet2 = new Pet(2, "Kipper", petype2, user);

		List<Pet> pets = new ArrayList<>();
		pets.add(pet);
		pets.add(pet2);

		when(petRepoMock.findByOwnerUsername(username)).thenReturn(pets);
		assertEquals(pets, petService.getPets(username));
	}
}
