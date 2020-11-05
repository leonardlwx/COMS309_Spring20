package petManagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import userManagement.User;
import petTypeManagement.PetType;
import petTypeManagement.PetTypeRepository;

/**
 * The Pet service accesses and modifies the database using the repository
 * 
 * @author Varun
 */
@Service
public class PetService {
	int id;

	@Autowired
	PetRepository petRepository;

	@Autowired
	PetTypeRepository petTypeRepository;

	/**
	 * Gets all Pets for a User
	 * 
	 * @param username The User's username
	 * @return A List of all the User's Pets
	 */
	public List<Pet> getPets(String username) {
		List<Pet> pets = new ArrayList<>();
		petRepository.findByOwnerUsername(username).forEach(pets::add);
		return pets;
	}

	/**
	 * Creates a new Pet for a User
	 * 
	 * @param rarity The pet rarity
	 * @param user   The user to whom the pet is to be added
	 */
	public String addPet(String rarity, User user) {
		try {
			id = petRepository.findFirstByOrderByIdDesc().getId() + 1;
		}

		catch (NullPointerException e) {

		}

		PetType petType = petTypeRepository.getRandomType();
		petRepository.save(new Pet(id, "Moe", petType, user));
		return petType.getSubType() + "_" + id;
	}

	/**
	 * Deletes a Pet from a User
	 * 
	 * @param id The id of the Pet to be deleted
	 */
	public void deletePet(int id) {
		petRepository.deleteById(id);
	}

	/**
	 * Renames a the Pet that goes by the given id
	 * 
	 * @param id   The Pet id
	 * @param name The new name for the Pet
	 */
	public void renamePet(int id, String name) {
		PetType type = petRepository.findById(id).get().getType();
		User owner = petRepository.findById(id).get().getOwner();
		petRepository.save(new Pet(id, name, type, owner));
	}
}
