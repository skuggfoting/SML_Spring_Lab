package se.sml.sdj.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import se.sml.sdj.model.Team;
import se.sml.sdj.model.User;
import se.sml.sdj.model.WorkItem;

/*
  Funktioner:
- Skapa ett team x
- Uppdatera ett team x
- Inaktivera ett team x
- Hämta alla team x
- Lägga till en User till ett team x
 */

public interface TeamRepository extends CrudRepository<Team, Long> {
	
	Team findByName(String name);
	
	List<Team> findAll();
	
	Long countByName(String name);
	
	@Query("select t.users from Team t where t.name = ?1")
	List<User> findUsersByTeam(String name);

	@Query("select t.users from Team t where t.name = ?1")
	List<WorkItem> findWorkItemsByTeam(String name);
	
//	select u.workItems from User u where u.username = ?1
	
//	@Transactional
//	List<Team> removeByName(String name);

}
