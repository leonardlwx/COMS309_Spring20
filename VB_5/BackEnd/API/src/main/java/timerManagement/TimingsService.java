package timerManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import userManagement.User;
import userManagement.UserRepository;

/**
 * The Timings service accesses and modifies the database using the repository
 * 
 */
@Service
public class TimingsService {
	@Autowired
	private TimingsRepository timingsRepository;

	@Autowired
	UserRepository userRepo;

	int id;

	public TimingsService() {

	}

	/**
	 * Gets a list of timings
	 * 
	 * @param username The users username
	 * @return A List of timings for the username
	 */
	public List<Timings> getTimings(String username) {
		List<Timings> timings = new ArrayList<>();
		timingsRepository.findByOwnerUsername(username).forEach(timings::add);
		return timings;
	}

	/**
	 * Adds a time interval
	 * 
	 * @param username  The users username
	 * @param startTime The start of the interval
	 * @param endTime   The end of the interval
	 */
	public void addTiming(String username, Date startTime, Date endTime) {
		try {
			id = timingsRepository.findFirstByOrderByIdDesc().getId() + 1;
		}

		catch (NullPointerException e) {

		}

		User user = userRepo.findById(username).get();
		timingsRepository.save(new Timings(id, startTime, endTime, user));
	}
}
