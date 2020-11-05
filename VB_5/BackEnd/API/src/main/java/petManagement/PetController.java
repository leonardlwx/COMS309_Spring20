package petManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import userManagement.User;
import petTypeManagement.PetType;
import petTypeManagement.PetTypeService;

/**
 * The Pet controller receives requests and calls the corresponding service
 * methods
 *
 * @author Varun
 */
@RestController
public class PetController {
	@Autowired
	private PetService petService;

	@Autowired
	private PetTypeService petTypeService;

	/**
	 * Gets all Pets for a User
	 * 
	 * @param username The User's username
	 * @return A List of all the User's Pets
	 */
	@RequestMapping("/users/{username}/pets")
	public List<Pet> getPets(@PathVariable String username) {
		return petService.getPets(username);
	}

	/**
	 * Renames a the Pet that goes by the given id
	 * 
	 * @param id   The Pet id
	 * @param name The new name for the Pet
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/users/pets/rename/{id}/{name}")
	public void renamePet(@PathVariable int id, @PathVariable String name) {
		petService.renamePet(id, name);
	}

	/**
	 * Creates a new Pet for a User
	 * 
	 * @param rarity The pet rarity
	 * @param user   The pet owner
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/users/pets/{rarity}")
	public String addPet(@PathVariable String rarity, @RequestBody User user) {
		return petService.addPet(rarity, user);
	}

	/**
	 * Deletes a Pet from a User
	 * 
	 * @param id The id of the Pet to be deleted
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/pets/{id}")
	public void deletePet(@PathVariable int id) {
		petService.deletePet(id);
	}

	/**
	 * Adds a new PetType to the DB
	 * 
	 * @param type    A type String
	 * @param subType A subType String
	 * @param rarity  The PetType rarity
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/pettypes/{type}/{subType}/{rarity}")
	public void addPetType(@PathVariable String type, @PathVariable String subType, @PathVariable String rarity) {
		petTypeService.addPetType(type, subType, rarity);
	}

	/**
	 * Displays all pet types in the DB
	 * 
	 * @return A List of all Pet Types
	 */
	@RequestMapping("/pettypes")
	public List<PetType> getPetTypes() {
		return petTypeService.getPetTypes();
	}

	/**
	 * Only for testing purposes
	 * 
	 * @return A Pet object
	 */
	@RequestMapping("/test")
	public Pet test() {
		return new Pet(0, "Kipper", new PetType(0, "Dog", "Terrier", "Common"), new User("9000", "ooooo", 1));
	}
}
