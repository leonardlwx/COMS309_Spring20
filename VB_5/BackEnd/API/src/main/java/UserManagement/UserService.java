package userManagement;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The User service accesses and modifies the database using the repository
 * 
 * @author Varun
 */
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	/**
	 * Constructor for easy construction of a UserService object
	 */
	public UserService() {

	}

	/**
	 * Gets a list of all users in the database
	 * 
	 * @return A List of all Users
	 */
	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	/**
	 * Gets the entry of the User with the username from the database
	 * 
	 * @param username The User's username
	 * @return The user as a User object
	 */
	public User getUser(String username) {
		return userRepository.findById(username).get();
	}

	/**
	 * Adds a new User to the database
	 * 
	 * @param username The User's username
	 * @param salt     The salt String
	 * @param password The hashed password
	 */
	public void addUser(String username, String salt, int password) {
		userRepository.save(new User(username, salt, password));
	}

	/**
	 * Deletes the entry of the User with the username from the database
	 * 
	 * @param username The User's username
	 */
	public void deleteUser(String username) {
		userRepository.delete(new User(username, "oooooo", 0));
	}

	/**
	 * Lists the number of used tickets a user has used
	 * 
	 * @param username The User's username
	 * @return The number of tickets the User has used
	 */
	public int getNumberOfUsedTickets(String username) {
		return userRepository.findById(username).get().getUsedTickets();
	}

	/**
	 * Lists the number of used tickets a user has
	 * 
	 * @param username The User's username
	 * @return The number of tickets the User has used
	 */
	public int getNumberOfTickets(String username) {
		return userRepository.findById(username).get().getTickets();
	}

	/**
	 * Adds the specified number of tickets to a users account
	 * 
	 * @param username The username of the User to whom the tickets are to be added
	 * @param tickets  The number of tickets to be added
	 */
	public void addTickets(String username, int tickets) {
		User user = userRepository.findById(username).get();
		user.addTickets(tickets);
		userRepository.save(user);
	}

	/**
	 * Removes a ticket from a Users account
	 * 
	 * @param username The username of the User from whom the ticket is to be
	 *                 removed
	 */
	public void removeTicket(String username) {
		User user = userRepository.findById(username).get();
		user.removeTicket();
		userRepository.save(user);
	}
}
