package se.sml.sdj.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import se.sml.sdj.model.User;
import se.sml.sdj.model.WorkItem;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	List<User> findByFirstName(String firstName);

	List<User> findByLastName(String lastName);

	User findByUsername(String username);
	
	List<User> findByFirstNameAndLastName(String firstName, String lastName);
	
	List<User> findByFirstNameAndLastNameAndUsername(String firstName, String lastName, String username);

	List<User> findByUserNumber(String userNumber);

	Long countByLastName(String lastName);
	
	@Transactional
	List<User> removeByLastName(String lastName);
	
//	Page<Employee> findByLastNameLike(String lastName, Pageable pageable);
	Slice<User> findByLastNameLike(String lastName, Pageable pageable);
	
//	List<Employee> getByNumber(String number);
	
//	@Query("select e from User e where e.employeeNumber = ?1")
	@Query("select e from #{#entityName} e where e.userNumber = ?1")
	List<User> getByNumber(String number);
	
	@Query("select u.workItems from User u where u.username = ?1")
	List<WorkItem> findWorkItemsByUser(String name);
	
	
//	@Query("select e from #{#entityName} e where e.Team_id = ?1")
//	List<User> findAll(Long number);
}























