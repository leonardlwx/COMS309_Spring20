package timerManagement;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import userManagement.User;

/**
 * Class to store details of time intervals
 * 
 */
@Entity
public class Timings {
	@Id
	private Integer id;
	private Date startTime;
	private Date endTime;

	@ManyToOne
	private User owner;

	/**
	 * Constructor for easy construction of a Timings object with default values
	 */
	public Timings() {

	}

	/**
	 * Constructor for easy construction of a User object with default values
	 * 
	 * @param id        The timing id
	 * @param startTime The start of a time interval
	 * @param endTime   The end of a time interval
	 * @param owner     The owner as a User object
	 */
	public Timings(int id, Date startTime, Date endTime, User owner) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.owner = owner;
	}

	/**
	 * 
	 * @return The timing id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @return The start of an interval
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * 
	 * @param startTime The start of an interval
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 
	 * @return the end of an interval
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 
	 * @param endTime The end of an interval
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 
	 * @return The owner of the timing
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * 
	 * @param owner The owner of the timing
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}
}
