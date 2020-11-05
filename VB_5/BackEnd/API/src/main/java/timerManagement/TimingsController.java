package timerManagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Timings controller receives requests and calls the corresponding service
 * methods
 *
 */
@RestController
public class TimingsController {
	@Autowired
	private TimingsService timingsService;

	/**
	 * Adds a start and end time for a study session to the database
	 * 
	 * @param username  The users username
	 * @param startTime The start of the interval
	 * @param endTime   The end of the interval
	 * @throws ParseException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/timings/{username}/{sTime}")
	public void addTimings(@PathVariable String username, @PathVariable String sTime, @RequestBody Date endTime) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss", Locale.ENGLISH);
			Date startTime = formatter.parse(sTime);

			timingsService.addTiming(username, startTime, endTime);
		}

		catch (Exception e) {
			System.out.println("ERROR: " + sTime);
			e.printStackTrace();
		}
	}

	/**
	 * Gets a list of timings
	 * 
	 * @param username The user whose timings are to be returned
	 * @return A List of time intervals
	 */
	@RequestMapping("/timings/{username}")
	public List<Timings> getTimings(@PathVariable String username) {
		return timingsService.getTimings(username);
	}

	@RequestMapping("/timings/test")
	public Date getTimings() {
		Date time = new Date();
		return time;
	}
}
