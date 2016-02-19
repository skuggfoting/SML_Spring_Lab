package se.sml.sdj;

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

			// Initiate Users
			User user1 = new User("CG16-12345", "Carl Gustav", "Bernadotte", "1001", "Active");
			User user2 = new User("SiBe-12345", "Silvia", "Bernadotte", "1002", "Active");
			User user3 = new User("ViCotte-12", "Victoria", "Bernadotte", "1003", "Active");
			User user4 = new User("Beth the Death", "Elisabeth", "Tarras-Wahlberg", "1004", "Active");
			
			// Save Users
			userService.save(user1)
						.save(user2)
						.save(user3)
						.save(user4);

			
			// Initiate Teams
			Team team1 = new Team("Hovet", "Active");
			Team team2 = new Team("PR", "Inactive");
			
			// Save Teams
			teamService.save(team1)
						.save(team2);

			
			// Initiate WorkItems
			WorkItem workItem1 = new WorkItem("App", "An app tha will make me good", "2001", "Done");
			WorkItem workItem2 = new WorkItem("App", "An app tha will make me bad", "2002", "Started");
			WorkItem workItem3 = new WorkItem("App", "An app tha will make me ugly", "2003", "Started");
			WorkItem workItem4 = new WorkItem("App", "An app tha will make me good, bad and ugly", "2004", "Started");
			WorkItem workItem5 = new WorkItem("App", "An app tha will make me god", "2005", "Started");
			WorkItem workItem6 = new WorkItem("App", "An app tha will make me a bad and ugly god", "2006", "Started");

			// Save WorkItems
			workItemService.save(workItem1)
							.save(workItem2)
							.save(workItem3)
							.save(workItem4)
							.save(workItem5)
							.save(workItem6);
			
			
			
			// Initiate Issue
			Issue issue1 = new Issue("Appen suger, knugen är missnöjd");

			// Add Issue to WorkItem
			workItemService.addIssue(workItem1, issue1);
			System.out.println("\nAdd Issue to WorkItem:\n" + workItem1);
			
			// Update Issue
			Issue issueUpdate = workItemService.findByWorkItemNumber("2001").getIssue();
			issueUpdate.setIssue("Monarkin fallerar, hjääääääälp...");
			workItemService.updateIssue(workItem1, issueUpdate);
			System.out.println("\nUpdate Issue:\n" + workItem1);


			// Add WorkItems to Users
			userService.addWorkItem("CG16-12345", workItem1)
						.addWorkItem("CG16-12345", workItem2)
						.addWorkItem("CG16-12345", workItem3)
						.addWorkItem("CG16-12345", workItem4)
						.addWorkItem("CG16-12345", workItem5)
						.addWorkItem("SiBe-12345", workItem6);
			
			// Adding Users to Team. A User can be in only one Team
			teamService.addUser("Hovet", user1)
						.addUser("Hovet", user2)
						.addUser("Hovet", user3)
						.addUser("PR", user4);
			
			//To make it fail(Adding same User to two Teams:
//			teamService.addUser("PR", user1);
			

			// User manipulation  /////////////////////////////////////////////////////////////////////////////////////////////			

			// Update Status for User (in this case inactivating the User at the same time)
			User userUpdate = userService.findByUserNumber("1003");
			userUpdate.setStatus("Inactive");
			userService.save(userUpdate);
			System.out.println("\nUpdated status and inactivation for User:\n" + userService.findByUserNumber("1003").getStatus());
			
			
			// Team manipulation  /////////////////////////////////////////////////////////////////////////////////////////////		
			
			// Update Status for Team (in this case inactivating the Team at the same time)
			Team teamUpdate = teamService.findByName("PR");
			teamUpdate.setStatus("Inactive");
			teamService.save(teamUpdate);
			System.out.println("\nUpdated status and inactivation for Team:\n" + teamService.findByName("PR").getStatus());
			

			// WorkItem manipulation
			
			// Update Status for WorkItem (in this case inactivating the WorkItem at the same time)
			WorkItem workItemUpdate = workItemService.findByWorkItemNumber("2003");
			workItemUpdate.setStatus("Inactive");
			workItemService.save(workItemUpdate);
			System.out.println("\nUpdated status and inactivation for WorkItem:\n" + workItemService.findByWorkItemNumber("2003").getStatus());
			
			
			// User queries: /////////////////////////////////////////////////////////////////////////////////////////////////

			// Find User by userNumber
			System.out.println("\nUserNumber:\n" + userService.findByUserNumber("1001"));

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














