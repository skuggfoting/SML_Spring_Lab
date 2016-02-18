package se.sml.sdj.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import se.sml.sdj.model.User;
import se.sml.sdj.model.WorkItem;

interface UserRepository extends PagingAndSortingRepository<User, Long> {

	List<User> findByFirstName(String firstName);

	List<User> findByLastName(String lastName);

	User findByUsername(String username);

	List<User> findByFirstNameAndLastName(String firstName, String lastName);

	List<User> findByFirstNameAndLastNameAndUsername(String firstName, String lastName, String username);

	User findByUserNumber(String userNumber);

	@Query("select u.workItems from User u where u.username = ?1")
	List<WorkItem> findWorkItemsByUser(String name);
}
