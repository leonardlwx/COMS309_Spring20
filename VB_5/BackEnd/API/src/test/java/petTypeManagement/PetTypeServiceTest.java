package petTypeManagement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PetTypeServiceTest {
	@Mock
	PetTypeRepository petTypeRepoMock;

	@InjectMocks
	PetTypeService petTypeService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void getPetTypesTest() {
		PetType petype = new PetType(90, "Cat", "Siamese Cat", "Common");
		PetType petype2 = new PetType(91, "Dog", "Border Collie", "Rare");

		List<PetType> pettypes = new ArrayList<>();
		pettypes.add(petype);
		pettypes.add(petype2);
		Iterable<PetType> ptIt = pettypes;

		when(petTypeRepoMock.findAll()).thenReturn(ptIt);
		assertEquals(pettypes, petTypeService.getPetTypes());
	}

}
