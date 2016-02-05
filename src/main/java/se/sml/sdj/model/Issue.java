package se.sml.sdj.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/*
  Funktioner:
- Skapa en Issue och lägga till den till en work item x
- Uppdatera en Issue 
- Hämta alla work item som har en Issue
 */

@Embeddable
public class Issue {
	@Column()
	private String description;

	protected Issue() {
	}

	public Issue(String issue) {
		this.description = issue;
	}

	public String getIssue() {
		return description;
	}

	public void addDescription(String issue) {
		this.description = issue;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
