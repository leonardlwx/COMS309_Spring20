package userManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The User controller receives requests and calls the corresponding service
 * methods
 *
 * @author Varun
 */
@RestController
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * Returns a list of all Users
	 * 
	 * @return A List of all Users
	 */
	@RequestMapping("/users")
	public List<User> getUsers() {
		return userService.getUsers();
	}

	/**
	 * Gets a User
	 * 
	 * @param username of the User to be returned
	 * @return a User object
	 */
	@RequestMapping("/users/{username}")
	public User getUser(@PathVariable String username) {
		return userService.getUser(username);
	}

	/**
	 * Creates a new User
	 * 
	 * @param username The User's username
	 * @param salt     The salt String
	 * @param password The hashed password
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/users/{username}/{salt}")
	public void addUser(@PathVariable String username, @PathVariable String salt, @RequestBody int password) {
		userService.addUser(username, salt, password);
	}

	/**
	 * Deletes a User
	 * 
	 * @param username The username of the User to be deleted
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{username}")
	public void deleteUser(@PathVariable String username) {
		userService.deleteUser(username);
	}

	/**
	 * Adds the specified number of tickets to a users account
	 * 
	 * @param username The username of the User to whom the tickets are to be added
	 * @param tickets  The number of tickets to be added
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/users/{username}/tickets")
	public void addTickets(@PathVariable String username, @RequestBody int tickets) {
		userService.addTickets(username, tickets);
	}

	/**
	 * Removes a ticket from a Users account
	 * 
	 * @param username The username of the User from whom the ticket is to be
	 *                 removed
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{username}/tickets")
	public void removeTicket(@PathVariable String username) {
		userService.removeTicket(username);
	}

	/**
	 * Lists the number of tickets a User has
	 * 
	 * @param username The username of the User whose tickets are to be listed
	 * @return The number of tickets the User has
	 */
	@RequestMapping("/users/{username}/tickets")
	public int getNumberOfTickets(@PathVariable String username) {
		return userService.getNumberOfTickets(username);
	}

	/**
	 * Lists the number of used tickets a user has used
	 * 
	 * @param username The username of the User whose used tickets are to be listed
	 * @return The number of tickets the User has used
	 */
	@RequestMapping("/users/{username}/tickets/used")
	public int getNumberOfUsedTickets(@PathVariable String username) {
		return userService.getNumberOfUsedTickets(username);
	}
}