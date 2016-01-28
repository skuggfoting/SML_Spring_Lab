package se.sml.sdj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import se.sml.sdj.service.exception.ServiceException;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(unique = true, nullable = false)
	private String userNumber;

	@Column(nullable = false)
	private String status;
	
	@ManyToOne // (cascade = CascadeType.PERSIST)
	private Team team;

	@OneToOne
	private WorkItem workItem;


	protected User() {
	}

	public User(String username, String firstName, String lastName, String userNumber, Team team, WorkItem workItem, String status) throws ServiceException {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userNumber = userNumber;
		this.team = team;
		this.workItem = workItem;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmployeeNumber() {
		return userNumber;
	}

	public Team getTeam() {
		return team;
	}

	public WorkItem getParkingSpot() {
		return workItem;
	}
	
	public String getStatus() {
		return status;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public void setParkingSpot(WorkItem workItem) {
		this.workItem = workItem;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	
//	@Override
//	public String toString()
//	{
//		return userId + " : " + username + " : " + password + " : " + status;
//	}

	@Override
	public boolean equals(Object otherObj)
	{
		if (this == otherObj)
		{
			return true;
		}

		if (otherObj instanceof User)
		{
			User otherUser = (User) otherObj;
			return this.username.equals(otherUser.username);
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		int result = 1;
		result += 37 * username.hashCode();
		return result;
	}
}
