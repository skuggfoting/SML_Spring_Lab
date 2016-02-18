package se.sml.sdj.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import se.sml.sdj.model.Team;
import se.sml.sdj.model.User;

interface TeamRepository extends CrudRepository<Team, Long> {

	Team findByName(String name);

	List<Team> findAll();

	Long countByName(String name);

	@Query("select t.users from Team t where t.name = ?1")
	List<User> findUsersByTeam(String name);
}
