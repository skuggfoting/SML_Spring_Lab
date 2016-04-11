package se.sml.sdj.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
public class Team {
	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;

	@Column(nullable = false)
	private String status;

	@OneToMany(fetch = FetchType.EAGER)
	private Collection<User> users;

	protected Team() {
	}

	public Team(String name, String status) {
		this.name = name;
		this.status = status;
		this.users = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public Team setName(String name) {
		this.name = name;
		return this;
	}

	public Team setStatus(String status) {
		this.status = status;
		return this;
	}

	public void addUser(User user) {
		users.add(user);
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

		if (otherObj instanceof Team) {
			Team otherTeam = (Team) otherObj;
			return this.name.equals(otherTeam.name);
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result += 37 * name.hashCode();
		return result;
	}
}
