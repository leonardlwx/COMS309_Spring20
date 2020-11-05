package petTypeManagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The PetType service accesses and modifies the database using the repository
 * 
 * @author Varun
 */
@Service
public class PetTypeService {
	private int id;

	@Autowired
	PetTypeRepository petTypeRepository;

	/**
	 * Displays all pet types in the DB
	 * 
	 * @return A List of all Pet Types
	 */
	public List<PetType> getPetTypes() {
		List<PetType> petTypes = new ArrayList<>();
		petTypeRepository.findAll().forEach(petTypes::add);
		return petTypes;
	}

	/**
	 * Adds a new PetType to the DB
	 * 
	 * @param type    A type String
	 * @param subType A subType String
	 * @param rarity  The PetType rarity
	 */
	public void addPetType(String type, String subType, String rarity) {
		try {
			id = petTypeRepository.findFirstByOrderByIdDesc().getId() + 1;
		}

		catch (NullPointerException e) {
			// Null Pointer when nothing is in the table
		}

		PetType petType = new PetType(id, type, subType, rarity);
		petTypeRepository.save(petType);
	}
}
