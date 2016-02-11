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
	private String note;

	protected Issue() {
	}

	public Issue(String note) {
		this.note = note;
	}

	public String getIssue() {
		return note;
	}

	public void setIssue(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
