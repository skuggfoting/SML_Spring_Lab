package se.sml.sdj.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import se.sml.sdj.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
	
	List<Team> findByName(String name);

	Long countByName(String name);

	@Transactional
	List<Team> removeByName(String name);
}
