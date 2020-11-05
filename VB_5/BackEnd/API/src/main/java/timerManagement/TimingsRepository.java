package timerManagement;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * The Timings repository contains inherited methods from the CrudRepsoitory for
 * database access
 *
 */
public interface TimingsRepository extends CrudRepository<Timings, Integer> {
	public List<Timings> findByOwnerUsername(String username);

	public Timings findFirstByOrderByIdDesc();
}
