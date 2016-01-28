package se.sml.sdj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import se.sml.sdj.service.exception.ServiceException;

@Entity
public class WorkItem {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String lable;

	@Column(nullable = false)
	private String description;

	@Column(unique = true, nullable = false)
	private String workItemNumber;

	@Column(nullable = false)
	private String status;

	protected WorkItem() {
	}

	public WorkItem(String lable, String description, String workItemNumber, String status) throws ServiceException {
		this.lable = lable;
		this.description = description;
		this.workItemNumber = workItemNumber;

		setStatus(status);
	}

	public Long getId() {
		return id;
	}

	public String getLable() {
		return lable;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setLable(String lable) {
		this.lable = lable;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	// @Override
	// public boolean equals(Object otherObj)
	// {
	// if (this == otherObj)
	// {
	// return true;
	// }
	//
	// if (otherObj instanceof User)
	// {
	// User otherUser = (User) otherObj;
	// return this.username.equals(otherUser.username);
	// }
	// return false;
	// }
	//
	// @Override
	// public int hashCode()
	// {
	// int result = 1;
	// result += 37 * username.hashCode();
	// return result;
	// }
}
