package userManagement;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class to store details of a User
 * 
 * @author Varun
 */
@Entity
@Table(name = "users")
public class User {
	@Id
	private String username;
	private String salt;
	private int password;
	private int tickets;
	private int usedTickets;

	/**
	 * Constructor for easy construction of a User object with default values
	 */
	public User() {

	}

	/**
	 * Constructor for construction of a User object with given values
	 * 
	 * @param username The User's username
	 * @param salt     The salt String
	 * @param password The hashed password
	 */
	public User(String username, String salt, int password) {
		this.username = username;
		this.salt = salt;
		this.password = password;
	}

	/**
	 * Constructor for construction of a User object with existing tickets
	 * 
	 * @param username    The User's username
	 * @param salt        The salt String
	 * @param password    The hashed password
	 * @param tickets     The number of tickets the user has
	 * @param usedTickets The number of tickets the User has used
	 */
	public User(String username, String salt, int password, int tickets, int usedTickets) {
		this.username = username;
		this.salt = salt;
		this.password = password;
		this.tickets = tickets;
		this.usedTickets = usedTickets;
	}

	/**
	 * 
	 * @return the User's username
	 */
	public String getId() {
		return username;
	}

	/**
	 * 
	 * @param username The User's username
	 */
	public void setId(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return The salt String
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * 
	 * @param salt The salt String
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * 
	 * @return The hashed password
	 */
	public int getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password The hashed password
	 */
	public void setPassword(int password) {
		this.password = password;
	}

	/**
	 * 
	 * @return The number of tickets the User has
	 */
	public int getTickets() {
		return tickets;
	}

	/**
	 * 
	 * @param tickets The number of tickets the User has
	 */
	public void setTickets(int tickets) {
		this.tickets = tickets;
	}

	/**
	 * 
	 * @return The number of tickets the User has used
	 */
	public int getUsedTickets() {
		return usedTickets;
	}

	/**
	 * 
	 * @param usedtickets The number of tickets the User has used
	 */
	public void setUsedtickets(int usedtickets) {
		this.usedTickets = usedtickets;
	}

	/**
	 * 
	 * @param tickets The number of tickets to be added to the User
	 */
	public void addTickets(int tickets) {
		this.tickets += tickets;
	}

	/**
	 * Removes a single ticket from the User
	 */
	public void removeTicket() {
		--tickets;
		++usedTickets;
	}
}
