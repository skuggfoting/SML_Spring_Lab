package se.sml.sdj.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import se.sml.sdj.model.Team;
import se.sml.sdj.model.User;

/*
  Funktioner:
- Skapa ett team x
- Uppdatera ett team x
- Inaktivera ett team x
- Hämta alla team x
- Lägga till en User till ett team x
 */

interface TeamRepository extends CrudRepository<Team, Long> {

	Team findByName(String name);

	List<Team> findAll();

	Long countByName(String name);

	@Query("select t.users from Team t where t.name = ?1")
	List<User> findUsersByTeam(String name);

	// @Query("select t.users from Team t where t.name = ?1")
	// List<WorkItem> findWorkItemsByTeam(String name);

	// select u.workItems from User u where u.username = ?1

	// @Transactional
	// List<Team> removeByName(String name);

}
