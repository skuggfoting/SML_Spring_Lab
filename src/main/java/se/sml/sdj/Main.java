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

public final class Main {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			context.scan("se.sml.sdj");
			context.refresh();

			UserService userService = context.getBean(UserService.class);
			TeamService teamService = context.getBean(TeamService.class);
			WorkItemService workItemService = context.getBean(WorkItemService.class);

			// Initiate Team
			Team team1 = new Team("Hovet", "Active");
			Team team2 = new Team("PR", "Inactive");

			// Initiate and create WorkItem
			WorkItem workItem1 = workItemService.save(new WorkItem("App", "An app tha will make me god", "2001", "Done"));
			WorkItem workItem2 = workItemService.save(new WorkItem("App", "An app tha will make me bad", "2002", "Started"));
			WorkItem workItem3 = workItemService.save(new WorkItem("App", "An app tha will make me ugly", "2003", "Started"));
			WorkItem workItem4 = workItemService.save(new WorkItem("App", "An app tha will make me god, bad and ugly", "2004", "Started"));
			WorkItem workItem5 = workItemService.save(new WorkItem("App", "An app tha will make me ugly", "2005", "Started"));
			WorkItem workItem6 = workItemService.save(new WorkItem("App", "An app tha will make me god, bad and ugly", "2006", "Started"));

			// Initiate User
			User user1 = new User("CG16-12345", "Carl Gustav", "Bernadotte", "1001", "Active");
			User user2 = new User("SiBe-12345", "Silvia", "Bernadotte", "1002", "Active");
			User user3 = new User("ViCotte-12", "Victoria", "Bernadotte", "1003", "Active");
			User user4 = new User("Beth the Death", "Elisabeth", "Tarras-Wahlberg", "1004", "Active");

			// Initiate Issue
			Issue issue1 = new Issue("Appen suger, knugen är missnöjd");

			// Adding Users to Team. A User can be in only one Team
			team1.addUser(user1);
			team1.addUser(user2);
			team1.addUser(user3);
			team2.addUser(user4);
			
			//To make it fail:
//			team2.addUser(user1);

			
			//Lekstuga
//			userService.save(user1);
//			userService.addWorkItem("CG16-12345", workItem1);
			
//			Collection<WorkItem> workItem = new ArrayList<>();
//			workItem.add(workItem1);
//			workItem.add(workItem2);
//			workItem.add(workItem3);
//			workItem.add(workItem4);
//			workItem.add(workItem5);
//			userService.addWorkItems("SiBe-12345", workItem);
//			
			//Lekstuga slutar
			
			
			// Add WorkItem to User
			user1.addWorkItem(workItem1);
			user1.addWorkItem(workItem2);
			user1.addWorkItem(workItem3);
			user1.addWorkItem(workItem4);
			user1.addWorkItem(workItem5);
			user4.addWorkItem(workItem6);

			// Add Issue to WorkItem
			workItemService.addIssue(workItem1, issue1);
			System.out.println(workItem1);
			
			// Update Issue
			issue1.setIssue("bajsmacka");
			workItemService.updateIssue(workItem1, issue1);
			System.out.println(workItem1);
			
			
			// Can only add Issue when WorkItem status is 'Done'
//			issue1.setIssue("Ny issue");
//			workItemService.addIssue(workItem1, issue1);
//			System.out.println(workItem1);
			
			// Create Users
			userService.save(user1);
			userService.save(user2);
			userService.save(user3);
			userService.save(user4);

			// Create Teams
			teamService.save(team1);
			teamService.save(team2);

			
			//Mer lekstuga
			// Update Status for user
//			userService.findByUserNumber("1001").setStatus("Inactive");
//			user1.setStatus("Inactive");
//			userService.save(user1);
			userService.updateStatus("CG16-12345", "Inactive");
//			//Lekstuaa slutar
			
			
			// User queries: /////////////////////////////////////////////////////////////////////////////////////////////////

			// Find User by userNumber
			System.out.println("\nUserNumber:" + userService.findByUserNumber("1001"));

			// Find User by firstName and lastName
			System.out.println("\nUser:");
			userService.findByFirstNameAndLastName("Silvia", "Bernadotte").forEach(System.out::println);
			// Find USer by firstName and lastName and username
			System.out.println("\nShit loads of different names:");
			userService.findByFirstNameAndLastNameAndUsername("Carl Gustav", "Bernadotte", "CG16-12345").forEach(System.out::println);

			// Get all Users from a Team
			System.out.println("\nAll Users from a Team:");
			userService.findUsersByTeam("Hovet").forEach(System.out::println);
			
			// Team queries: /////////////////////////////////////////////////////////////////////////////////////////////////

			// Get all Teams
			System.out.println("\nAll Teams:");
			teamService.findAll().forEach(System.out::println);
			
			// WorkItem queries: /////////////////////////////////////////////////////////////////////////////////////////////

			// Get workItem by Status
			System.out.println("\nAll WorkItem for specified Status:");
			workItemService.findByStatus("Started").forEach(System.out::println);

			// Get all workItems for a Team
			System.out.println("\nAll WorkItems for a specified Team:");
			workItemService.findWorkItemsByTeam("Hovet").forEach(System.out::println);

			// Get all workItem for a User
			System.out.println("\nAll WorkItems from a User:");
			userService.findWorkItemsByUser("CG16-12345").forEach(System.out::println);

			// Get all workItems containing a specified text-string
			System.out.println("\nAll WorkItem containing specified text-string:");
			workItemService.findByDescriptionContaining("ugly").forEach(System.out::println);

			// Get all workItems with an Issue
			System.out.println("\nAll WorkItems with an Issue");
			workItemService.findAllByIssue().forEach(System.out::println);
			
		}
	}
}














