package se.sml.sdj.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import se.sml.sdj.service.exception.ServiceException;

@Entity
@Table(name = "Users")
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

	// @ManyToOne // (cascade = CascadeType.PERSIST)
	//// @JoinColumn(name="id")
	// private Team team;

	@OneToMany(fetch = FetchType.EAGER)
	private Collection<WorkItem> workItems;

	protected User() {
	}

	public User(String username, String firstName, String lastName, String userNumber, String status) throws ServiceException {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userNumber = userNumber;
		this.status = status;
		this.workItems = new ArrayList<>();
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

	public String getUserNumber() {
		return userNumber;
	}

	public Collection<WorkItem> getWorkItem() {
		return workItems;
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

	public void addWorkItem(WorkItem workItem) {
		workItems.add(workItem);
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	@Override
	public boolean equals(Object otherObj) {
		if (this == otherObj) {
			return true;
		}

		if (otherObj instanceof User) {
			User otherUser = (User) otherObj;
			return this.username.equals(otherUser.username);
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result += 37 * username.hashCode();
		return result;
	}
}
