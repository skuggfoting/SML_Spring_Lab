package se.sml.sdj.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import se.sml.sdj.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByFirstName(String firstName);

	List<User> findByLastNameContaining(String value);

	List<User> findByFirstNameAndLastName(String firstName, String lastName);

	Long countByLastName(String lastName);
	
	@Transactional
	List<User> removeByLastName(String lastName);
	
//	Page<Employee> findByLastNameLike(String lastName, Pageable pageable);
	Slice<User> findByLastNameLike(String lastName, Pageable pageable);
	
//	List<Employee> getByNumber(String number);
	
//	@Query("select e from User e where e.employeeNumber = ?1")
	@Query("select e from #{#entityName} e where e.userNumber = ?1")
	List<User> getByNumber(String number);
}