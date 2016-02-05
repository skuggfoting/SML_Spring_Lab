package se.sml.sdj;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import se.sml.sdj.model.Issue;
import se.sml.sdj.model.Team;
import se.sml.sdj.model.User;
import se.sml.sdj.model.WorkItem;
import se.sml.sdj.repository.UserRepository;
//import se.sml.sdj.service.TeamRepository;
import se.sml.sdj.service.TeamService;
import se.sml.sdj.service.UserService;
//import se.sml.sdj.service.WorkItemRepository;
import se.sml.sdj.service.WorkItemService;
import se.sml.sdj.service.exception.ServiceException;

public final class Main {

	public static void main(String[] args) throws ServiceException {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.scan("se.sml.sdj");
			context.refresh();
			
			//Måste vara UserService för att save i userSevice ska fungera. Annars används crudens save.
//			UserRepository userRepository = context.getBean(UserRepository.class);
//			TeamRepository teamRepository = context.getBean(TeamRepository.class);
//			WorkItemRepository workItemRepository = context.getBean(WorkItemRepository.class);
			UserService userService = context.getBean(UserService.class);
			TeamService teamService = context.getBean(TeamService.class);
			WorkItemService workItemService = context.getBean(WorkItemService.class);
	
			Team team1 = new Team("Hovet");
			Team team2 = new Team("PR");
		
			WorkItem workItem1 = workItemService.save(new WorkItem("App", "An app tha will make me god", "1001", "Started"));
			WorkItem workItem2 = workItemService.save(new WorkItem("App", "An app tha will make me bad", "1002", "Started"));
			WorkItem workItem3 = workItemService.save(new WorkItem("App", "An app tha will make me ugly", "1003", "Started"));
			WorkItem workItem4 = workItemService.save(new WorkItem("App", "An app tha will make me god, bad and ugly", "1004", "Started"));
			WorkItem workItem5 = workItemService.save(new WorkItem("App", "An app tha will make me ugly", "1005", "Started"));
			WorkItem workItem6 = workItemService.save(new WorkItem("App", "An app tha will make me god, bad and ugly", "1006", "Started"));
			
//			userService.save(new User("CG16", "Carl Gustav", "Bernadotte", "1", team1, workItem1, "Active"));

//			employeeRepository.findAll().forEach(System.out::println);

			User user1 = new User("CG16-12345", "Carl Gustav", "Bernadotte", "1", "Active");
			User user2 = new User("SiBe-12345", "Silvia", "Bernadotte", "2", "Active");
			User user3 = new User("ViCotte-12", "Victoria", "Bernadotte", "3", "Active");
			User user4 = new User("Beth the Death", "Elisabeth", "Tarras-Wahlberg", "4", "Active");
			
			Issue issue1 = new Issue("Appen suger, knugen är missnöjd");

			team1.addUser(user1);
			team1.addUser(user2);
			team1.addUser(user3);
			team2.addUser(user4);
//			team2.addUser(user1);
			
//			workItem1.addUser(user1);
			
			user1.addWorkItem(workItem1);
			user1.addWorkItem(workItem2);
			user1.addWorkItem(workItem3);
			user1.addWorkItem(workItem4);
			user1.addWorkItem(workItem5);
			user2.addWorkItem(workItem6);
			
			workItem1.addIssue(issue1);
			System.out.println(workItem1);
			issue1.addDescription("Ny issue");
			workItem1.addIssue(issue1);
			System.out.println(workItem1);
			
			//Update Status for user
//			user1.setStatus("Inactive");
//			userService.save(user1);
			
			userService.save(user1);
			userService.save(user2);
			userService.save(user3);
			userService.save(user4);
			
			teamService.save(team1);
			teamService.save(team2);
			
			workItemService.save(workItem1);

//			System.out.println("\nUser:");
//			userRepository.findByFirstNameAndLastName("Silvia", "Bernadotte").forEach(System.out::println);
////			userRepository.findByLastNameContaining("Bern").forEach(System.out::println);
//			System.out.println(userRepository.countByLastName("Bernadotte"));
//			
//			System.out.println("\nTeam:\n" + teamService.findByName("Hovet"));
//
//			System.out.println("\nAll Teams:");
//			teamService.findAll().forEach(System.out::println);			
//			
//			System.out.println("\nUserNumber:");
//			userRepository.findByUserNumber("1").forEach(System.out::println);
//			
//			System.out.println("\nShit loads of different names:");
//			userRepository.findByFirstNameAndLastNameAndUsername("Carl Gustav", "Bernadotte", "CG16-12345").forEach(System.out::println);
//			
//			System.out.println("\nAll Users from a Team:");
//			teamService.findUsersByTeam("Hovet").forEach(System.out::println);

			
			
			
// WorkItem queries:
			
			System.out.println("\nAll WorkItem for specified Status:");
			workItemService.findByStatus("Unstarted").forEach(System.out::println);;
			
			
			
			System.out.println("\nAll WorkItems for a specified Team:");
			workItemService.findWorkItemsByTeam("Hovet").forEach(System.out::println); 
//			teamRepository.findByName("Hovet").getUsers().forEach(u -> u.getWorkItem().forEach(System.out::println)); 
			
			
//			System.out.println("\nAll WorkItem for specified Team: version 2");
//			teamRepository.findWorkItemsByTeam("Hovet").forEach(System.out::println); 
			
			
			
//			
//			System.out.println("\nAll WorkItems from a User:");
//			userRepository.findWorkItemsByUser("CG16-12345").forEach(System.out::println);
//			
//			
//			
//			System.out.println("\nAll WorkItem containing specified text-string:");
//			workItemService.findByDescriptionContaining("ugly").forEach(System.out::println);;
//			
//						
//			Slice<User> result = userRepository.findByLastNameLike("Bernadotte%", new PageRequest(0, 5));
//			System.out.println("\nLastNameLike:");
//			result.forEach(System.out::println);
//			
//			System.out.println("\nUsernr:\n" + userRepository.getByNumber("1"));
			
		}
	}

}