package se.sml.sdj.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import se.sml.sdj.model.WorkItem;

/*
 Funktioner:
- Skapa en work item x
- Ändra status på en work item [Unstarted, Started, Done] x
- Ta bort* en work item x
- Tilldela en work item till en User x
- Hämta alla work item baserat på status x
- Hämta alla work item för ett Team x
- Hämta alla work item för en User x
- Söka efter work item som innehåller en viss text i sin beskrivning x
 */

interface WorkItemRepository extends CrudRepository<WorkItem, Long> {

	List<WorkItem> findByStatus(String lable);

	List<WorkItem> findByDescriptionContaining(String value);

	WorkItem findByWorkItemNumber(String workItemNumber);

//	List<WorkItem> getByIssueNotNull();
	
	@Query("select w from WorkItem w where w.issue IS NOT NULL")
	List<WorkItem> getByIssue();
	
	Long countByLable(String lable);

	@Transactional
	List<WorkItem> removeByLable(String lable);

}
