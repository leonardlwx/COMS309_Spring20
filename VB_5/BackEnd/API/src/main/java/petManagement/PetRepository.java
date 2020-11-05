package petManagement;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * The Pet repository contains inherited methods from the CrudRepsoitory for
 * database access It also contains some methods that are automatically
 * generated from their names
 * 
 * @author Varun
 */
public interface PetRepository extends CrudRepository<Pet, Integer> {
	public List<Pet> findByOwnerUsername(String username);

	public Pet findFirstByOrderByIdDesc();
}
