package petManagement;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import userManagement.User;
import petTypeManagement.PetType;

/**
 * Class to represent and store pet details
 * 
 * @author Varun
 */
@Entity
public class Pet {
	@Id
	private Integer id;
	private String name;

	@ManyToOne
	private PetType type;

	@ManyToOne // (cascade = {CascadeType.ALL})
	private User owner;

	/**
	 * Constructor for easy construction of a Pet object with default values
	 */
	public Pet() {

	}

	/**
	 * Constructor for construction of a Pet object with given values
	 * 
	 * @param id    The Pet id
	 * @param name  The Pets name
	 * @param type  The PetType
	 * @param owner The Pets owner
	 */
	public Pet(int id, String name, PetType type, User owner) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.owner = owner;
	}

	/**
	 * 
	 * @return The Pet id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @return The Pets name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return The PetType
	 */
	public PetType getType() {
		return type;
	}

	/**
	 * 
	 * @return The Pets owner as a User object
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * 
	 * @param name The Pets name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @param type The PetType
	 */
	public void setType(PetType type) {
		this.type = type;
	}

	/**
	 * 
	 * @param owner The owner as a User object
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * An overriden equals method for comparison
	 */
	@Override
	public boolean equals(Object o) {
		return ((name.equals(((Pet) o).name)) && (type.equals(((Pet) o).type)) && (owner.equals(((Pet) o).owner)));
	}
}
