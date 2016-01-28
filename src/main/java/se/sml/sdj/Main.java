package se.sml.sdj;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import se.sml.sdj.model.Team;
import se.sml.sdj.model.User;
import se.sml.sdj.model.WorkItem;
import se.sml.sdj.repository.TeamRepository;
import se.sml.sdj.repository.UserRepository;
import se.sml.sdj.service.exception.ServiceException;
import se.sml.sdj.repository.WorkItemRepository;

public final class Main {

	public static void main(String[] args) throws ServiceException {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.scan("se.sml.sdj");
			context.refresh();
			
			UserRepository userRepository = context.getBean(UserRepository.class);
			TeamRepository teamRepository = context.getBean(TeamRepository.class);
			WorkItemRepository workItemRepository = context.getBean(WorkItemRepository.class);
		
			Team team1 = teamRepository.save(new Team("Hovet"));
			Team team2 = teamRepository.save(new Team("PR"));
		
			WorkItem workItem1 = workItemRepository.save(new WorkItem("App", "An app tha will make me god", "1001", "Unstarted"));
			WorkItem workItem2 = workItemRepository.save(new WorkItem("App", "An app tha will make me bad", "1002", "Started"));
			WorkItem workItem3 = workItemRepository.save(new WorkItem("App", "An app tha will make me ugly", "1003", "Done"));
			WorkItem workItem4 = workItemRepository.save(new WorkItem("App", "An app tha will make me god, bad and ugly", "1004", "Inactive"));

//			employeeRepository.findAll().forEach(System.out::println);

			userRepository.save(new User("CG16", "Carl Gustav", "Bernadotte", "1", team1, workItem1, "Active"));
			userRepository.save(new User("SiBe", "Silvia", "Bernadotte", "2", team1, workItem2, "Inactive"));
			userRepository.save(new User("ViCotte", "Victoria", "Bernadotte", "3", team1, workItem3, "Active"));
			userRepository.save(new User("Beth the Death", "Elisabeth", "Tarras-Wahlberg", "4", team2, workItem4, "Active"));
			
			System.out.println("\n Employee:");
			userRepository.findByFirstNameAndLastName("Silvia", "Bernadotte").forEach(System.out::println);
			userRepository.findByLastNameContaining("Bern").forEach(System.out::println);
			System.out.println(userRepository.countByLastName("Bernadotte"));
			
			System.out.println("\n Department:");
			teamRepository.findByName("Hovet").forEach(System.out::println);
			
			System.out.println("\n ParkingSpot:");
			workItemRepository.findByLable("Slottsgaraget").forEach(System.out::println);;
			
//			employeeRepository.save(new Employee("Master", "Yoda", "1001", new Address("street", "postal", "zip")));
//			System.out.println(employeeRepository.findByAddressStreet("street"));
			
//			repository.removeByLastName("Yoda").forEach(System.out::println);

			
			// Find one employee
//			System.out.println(employeeRepository.findOne(employee.getId()));
			
			// Delete employee
//			employeeRepository.delete(employee);
//			
//			// Find employee again 
//			System.out.println(employeeRepository.findOne(employee.getId()));
			
			Slice<User> result = userRepository.findByLastNameLike("Bernadotte%", new PageRequest(0, 5));
			System.out.println("\nLastNameLike:");
			result.forEach(System.out::println);
			
			System.out.println("\nUsernr:\n" + userRepository.getByNumber("1"));
			
		}
	}

}