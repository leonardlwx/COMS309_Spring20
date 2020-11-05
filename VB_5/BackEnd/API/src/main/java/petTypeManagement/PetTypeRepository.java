package petTypeManagement;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * The PetType repository contains inherited methods from the CrudRepsoitory for
 * database access It also contains methods implemented through SQL Queries
 * 
 * @author Varun
 */
public interface PetTypeRepository extends CrudRepository<PetType, Integer> {
	public PetType findFirstByOrderByIdDesc();

	@Query(value = "SELECT * FROM pet_type " + "WHERE rarity='common' " + "ORDER BY RAND() "
			+ "LIMIT 1", nativeQuery = true)
	public PetType getRandomType();
}
