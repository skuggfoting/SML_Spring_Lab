package se.sml.sdj.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import se.sml.sdj.model.User;
import se.sml.sdj.model.WorkItem;

interface UserRepository extends PagingAndSortingRepository<User, Long> {

	List<User> getByFirstName(String firstName);

	List<User> getByLastName(String lastName);

	User getByUsername(String username);

	List<User> getByFirstNameAndLastName(String firstName, String lastName);

	List<User> getByFirstNameAndLastNameAndUsername(String firstName, String lastName, String username);

	User getByUserNumber(String userNumber);

	@Query("select u.workItems from User u where u.username = ?1")
	List<WorkItem> getWorkItemsByUser(String username);
}
