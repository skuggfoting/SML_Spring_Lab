package se.sml.sdj.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
	
	@Embedded
	private Issue issue;

	protected WorkItem() {
	}

	public WorkItem(String lable, String description, String workItemNumber, String status) {
		this.lable = lable;
		this.description = description;
		this.workItemNumber = workItemNumber;
		this.status = status;
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
	
	public Issue getIssue() {
		return issue;
	}
	
	public void setLable(String lable) {
		this.lable = lable;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void addIssue(Issue issue) {
		this.issue = issue;
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

		if (otherObj instanceof WorkItem) {
			WorkItem otherWorkItem = (WorkItem) otherObj;
			return this.workItemNumber.equals(otherWorkItem.workItemNumber);
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result += 37 * workItemNumber.hashCode();
		return result;
	}
}
