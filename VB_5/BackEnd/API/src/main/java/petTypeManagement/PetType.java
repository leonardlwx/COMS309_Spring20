package petTypeManagement;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Class to store details of a type of Pet
 * 
 * @author Varun
 */
@Entity
public class PetType {
	@Id
	private int id;
	private String type;
	private String subType;
	private String rarity;

	/**
	 * Constructor for easy construction of a PetType object with default values
	 */
	public PetType() {

	}

	/**
	 * Constructor for construction of a Pet object with given values
	 * 
	 * @param id      The pet id
	 * @param type    The pet type
	 * @param subType The pet subtype
	 * @param rarity  The pet rarity
	 */
	public PetType(int id, String type, String subType, String rarity) {
		super();
		this.id = id;
		this.type = type;
		this.subType = subType;
		this.rarity = rarity;
	}

	/**
	 * 
	 * @return The Pet id
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id The Pet id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return The pet type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type The pet type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @return The pet sub-type
	 */
	public String getSubType() {
		return subType;
	}

	/**
	 * 
	 * @param subType The pet sub-type
	 */
	public void setSubType(String subType) {
		this.subType = subType;
	}

	/**
	 * 
	 * @return The pet rarity
	 */
	public String getRarity() {
		return rarity;
	}

	/**
	 * 
	 * @param rarity The pet rarity
	 */
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	/**
	 * An overriden equals method for comparison
	 */
	@Override
	public boolean equals(Object o) {
		return (subType.equals(((PetType) o).subType));
	}
}
