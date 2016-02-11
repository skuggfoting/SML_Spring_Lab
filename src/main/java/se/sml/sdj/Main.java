package se.sml.sdj;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.sml.sdj.model.Issue;
import se.sml.sdj.model.Team;
import se.sml.sdj.model.User;
import se.sml.sdj.model.WorkItem;
import se.sml.sdj.service.TeamService;
import se.sml.sdj.service.UserService;
import se.sml.sdj.service.WorkItemService;
import se.sml.sdj.service.exception.ServiceException;

public final class Main {

	public static void main(String[] args) throws ServiceException {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.scan("se.sml.sdj");
			context.refresh();

			UserService userService = context.getBean(UserService.class);
			TeamService teamService = context.getBean(TeamService.class);
			WorkItemService workItemService = context.getBean(WorkItemService.class);

			Team team1 = new Team("Hovet");
			Team team2 = new Team("PR");

			WorkItem workItem1 = workItemService.save(new WorkItem("App", "An app tha will make me god", "2001", "Started"));
			WorkItem workItem2 = workItemService.save(new WorkItem("App", "An app tha will make me bad", "2002", "Started"));
			WorkItem workItem3 = workItemService.save(new WorkItem("App", "An app tha will make me ugly", "2003", "Started"));
			WorkItem workItem4 = workItemService.save(new WorkItem("App", "An app tha will make me god, bad and ugly", "2004", "Started"));
			WorkItem workItem5 = workItemService.save(new WorkItem("App", "An app tha will make me ugly", "2005", "Started"));
			WorkItem workItem6 = workItemService.save(new WorkItem("App", "An app tha will make me god, bad and ugly", "2006", "Started"));

			// userService.save(new User("CG16", "Carl Gustav", "Bernadotte",
			// "1", team1, workItem1, "Active"));

			// employeeRepository.findAll().forEach(System.out::println);

			User user1 = new User("CG16-12345", "Carl Gustav", "Bernadotte", "1001", "Active");
			User user2 = new User("SiBe-12345", "Silvia", "Bernadotte", "1002", "Active");
			User user3 = new User("ViCotte-12", "Victoria", "Bernadotte", "1003", "Active");
			User user4 = new User("Beth the Death", "Elisabeth", "Tarras-Wahlberg", "1004", "Active");

			Issue issue1 = new Issue("Appen suger, knugen är missnöjd");

			team1.addUser(user1);
			team1.addUser(user2);
			team1.addUser(user3);
			team2.addUser(user4);
			// team2.addUser(user1);

			
			//Lekstuga
//			userService.addWorkItem("CG16-12345", workItem1);
//			
//			Collection<WorkItem> workItem = new ArrayList<>();
//			workItem.add(workItem1);
//			workItem.add(workItem2);
//			workItem.add(workItem3);
//			workItem.add(workItem4);
//			workItem.add(workItem5);
//			userService.addWorkItems("SiBe-12345", workItem);
//			
			//Lekstuga slutar
			
			user1.addWorkItem(workItem1);
			user1.addWorkItem(workItem2);
			user1.addWorkItem(workItem3);
			user1.addWorkItem(workItem4);
			user1.addWorkItem(workItem5);
			user4.addWorkItem(workItem6);

			workItem1.addIssue(issue1);
			System.out.println(workItem1);
			issue1.setIssue("Ny issue");
			workItem1.addIssue(issue1);
			System.out.println(workItem1);
			
			userService.save(user1);
			userService.save(user2);
			userService.save(user3);
			userService.save(user4);

			teamService.save(team1);
			teamService.save(team2);

			workItemService.save(workItem1);

			// Update Status for user
			userService.findByUserNumber("1001").setStatus("Inactive");
			// user1.setStatus("Inactive");
			userService.save(user1);
			userService.updateStatus("CG16-12345", "Inactive");
			
			
			// User queries:

			System.out.println("\nUser:");
			userService.findByFirstNameAndLastName("Silvia", "Bernadotte").forEach(System.out::println);

			System.out.println("\nShit loads of different names:");
			userService.findByFirstNameAndLastNameAndUsername("Carl Gustav", "Bernadotte", "CG16-12345").forEach(System.out::println);

			System.out.println("\nUserNumber:" + userService.findByUserNumber("1001"));

			System.out.println("\nAll Users from a Team:");
			userService.findUsersByTeam("Hovet").forEach(System.out::println);
			
			// Team queries:

			System.out.println("\nAll Teams:");
			teamService.findAll().forEach(System.out::println);

			System.out.println("\nSpecific Team:\n" + teamService.findByName("Hovet"));

			// WorkItem queries:

			System.out.println("\nAll WorkItem for specified Status:");
			workItemService.findByStatus("Unstarted").forEach(System.out::println);

			System.out.println("\nAll WorkItems for a specified Team:");
			workItemService.findWorkItemsByTeam("Hovet").forEach(System.out::println);

			System.out.println("\nAll WorkItems from a User:");
			userService.findWorkItemsByUser("CG16-12345").forEach(System.out::println);

			System.out.println("\nAll WorkItem containing specified text-string:");
			workItemService.findByDescriptionContaining("ugly").forEach(System.out::println);

			System.out.println("\nUsernr:\n" + userService.findByUserNumber("1001"));
		}
	}
}
