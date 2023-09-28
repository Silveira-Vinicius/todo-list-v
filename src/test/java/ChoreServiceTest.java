
import org.example.Chore;
import org.example.ChoreService;
import org.example.Exceptions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ChoreServiceTest {

    @Test
    void addChoreWhenTheDescriptionIsInvalidThrowAnException() {
        ChoreService service = new ChoreService();
        assertAll(
                () -> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore(null, null)),
                () -> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore("", null)),
                () -> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore(null, LocalDate.now().plusDays(1))),
                () -> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore("", LocalDate.now().plusDays(1))),
                () -> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore(null, LocalDate.now().minusDays(1))),
                () -> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore("", LocalDate.now().minusDays(1)))
        );
    }

    @Test
    @DisplayName("#addChore > When the deadline is invalid > Throw an exception")
    void addChoreWhenTheDeadlineIsInvalidThrowAnException() {
        ChoreService service = new ChoreService();
        assertAll(
                () -> assertThrows(InvalidDeadlineException.class,
                        () -> service.addChore("Description", null)),
                () -> assertThrows(InvalidDeadlineException.class,
                        () -> service.addChore("Description", LocalDate.now().minusDays(1)))
        );
    }

    @Test
    @DisplayName("#addChore > When adding a chore > When the chore already exists > Throw an exception")
    void addChoreWhenAddingAChoreWhenTheChoreAlreadyExistsThrowAnException() {
        ChoreService service = new ChoreService();
        service.addChore("Description", LocalDate.now());
        assertThrows(DuplicatedChoreException.class,
                () -> service.addChore("Description", LocalDate.now()));
    }

    /*
     * TODO: Create The following test cases:
     * 1. When adding a single chore. Compare the results (description, deadline, and isCompleted)
     * 2. When adding more than one chore. Also compare the results.
     *
     */

    @Test
    @DisplayName("#addChore > When adding a single Chore > Chore Added with correct description, deadline, and isCompleted")
    void addSingleChore() {
        ChoreService service = new ChoreService();
        String description = "Description of single chore";
        LocalDate deadline = LocalDate.now().plusDays(1);

        Chore addedChore = null;
        try {
            addedChore = service.addChore(description, deadline);
        } catch (Exception e) {
            fail("Adding the chore should not throw an exception");
        }
        Chore finalAddedChore = addedChore;
        assertAll("Chore details",
                () -> assertNotNull(finalAddedChore, "Chore should not be null"),
                () -> assertEquals(description, finalAddedChore.getDescription(), "Incorrect description"),
                () -> assertEquals(deadline, finalAddedChore.getDeadline(), "Incorrect deadline"),
                () -> assertFalse(finalAddedChore.getIsCompleted(), "Chore should not be completed")
        );
    }
    @Test
    @DisplayName("#addChore > When adding multiple Chores to the same ChoreService > Chores Added with correct details")
    void addMultipleChoresToSameService() {
        ChoreService service = new ChoreService();

        String description1 = "Description of chore 1";
        LocalDate deadline1 = LocalDate.now().plusDays(1);

        String description2 = "Description of chore 2";
        LocalDate deadline2 = LocalDate.now().plusDays(2);

        String description3 = "Description of chore 3";
        LocalDate deadline3 = LocalDate.now().plusDays(3);

        Chore addedChore1 = service.addChore(description1, deadline1);

        Chore addedChore2 = service.addChore(description2, deadline2);

        Chore addedChore3 = service.addChore(description3, deadline3);

        assertAll("Chores details",
                () -> assertNotNull(addedChore1, "Chore 1 should not be null"),
                () -> assertEquals(description1, addedChore1.getDescription(), "Incorrect description for chore 1"),
                () -> assertEquals(deadline1, addedChore1.getDeadline(), "Incorrect deadline for chore 1"),
                () -> assertFalse(addedChore1.getIsCompleted(), "Chore 1 should not be completed"),

                () -> assertNotNull(addedChore2, "Chore 2 should not be null"),
                () -> assertEquals(description2, addedChore2.getDescription(), "Incorrect description for chore 2"),
                () -> assertEquals(deadline2, addedChore2.getDeadline(), "Incorrect deadline for chore 2"),
                () -> assertFalse(addedChore2.getIsCompleted(), "Chore 2 should not be completed"),

                () -> assertNotNull(addedChore3, "Chore 2 should not be null"),
                () -> assertEquals(description3, addedChore3.getDescription(), "Incorrect description for chore 2"),
                () -> assertEquals(deadline3, addedChore3.getDeadline(), "Incorrect deadline for chore 2"),
                () -> assertFalse(addedChore3.getIsCompleted(), "Chore 2 should not be completed")
        );
    }
    @Test
    @DisplayName("#delete chore: when the list is empty")
    void deleteChoreWhenTheListIsEmpty(){
        ChoreService service = new ChoreService();
        assertThrows(EmptyChoreListException.class,
                () -> {
                    service.deleteChore("Qualquer coisa", LocalDate.now());
                });
    }

    @Test
    @DisplayName("#delete chore: when the list is not empty")
    void deleteUnexistantChoreThrowsaException(){
        ChoreService service = new ChoreService();
        service.addChore("Description", LocalDate.now());
        assertThrows(ChoreNotFoundException.class,
                () -> {
                    service.deleteChore("Chore to be deleted", LocalDate.now().plusDays(1));
                });

    }

    @Test
    @DisplayName("#delete chore: when the list is not empty and the chore exists")
    void deleteExistantChore(){
        ChoreService service = new ChoreService();
        service.addChore("Chore #01", LocalDate.now().plusDays(1));
        assertEquals(1, service.getChores().size());
        assertDoesNotThrow(() -> service.deleteChore("Chore #01", LocalDate.now().plusDays(1)));
        assertEquals(0, service.getChores().size());
    }
    @Test
    @DisplayName("#deleteChore > When the list is not empty > When the chore exists > Delete the chore")
    void deleteChoreWhenTheListIsNotEmptyWhenTheChoreExistsDeleteTheChore() {
        ChoreService service = new ChoreService();

        service.addChore("Chore #01", LocalDate.now().plusDays(1));
        assertEquals(1, service.getChores().size());

        assertDoesNotThrow(() -> service.deleteChore("Chore #01", LocalDate.now().plusDays(1)));
        assertEquals(0, service.getChores().size());
    }

    @Test
    @DisplayName("#toggleChore > Chore with valid deadline > Toggle chore")
    void toggleChoreWhenDeadlineIsValidToggleTheChore(){
        ChoreService service = new ChoreService();
        service.addChore("Chore #01", LocalDate.now());
        assertFalse(service.getChores().get(0).getIsCompleted());
        assertDoesNotThrow(() -> service.toggleChore("Chore #01", LocalDate.now()));
        assertTrue(service.getChores().get(0).getIsCompleted());
    }

    @Test
    @DisplayName("#toggleChore > Chore does not exist > Throws an exception")
    void toggleChoreWhenChoreIsNonexistent(){
        ChoreService service = new ChoreService();
        assertThrows(ChoreNotFoundException.class, () ->service.toggleChore("Chore", LocalDate.now()));
    }
    @Test
    @DisplayName("#toggleChore > When deadline is invalid > When the status is uncompleted > toggle the chore")
    void toggleChoreWhenDeadlineIsInvalid(){
        ChoreService service = new ChoreService();
        service.addChore("Chore #01", LocalDate.now());
        service.getChores().get(0).setDeadline(LocalDate.now().minusDays(1));
        assertFalse(service.getChores().get(0).getIsCompleted());
        assertDoesNotThrow(() -> service.toggleChore("Chore #01", LocalDate.now().minusDays(1)));
        assertTrue(service.getChores().get(0).getIsCompleted());
    }
    @Test
    @DisplayName("#toggleChore > When deadline is valid > When toggle the chore twice > toggle the chore")
    void toggleChoreWhenDeadlineIsValidWhenToggleTheChoreTwiceToggleTheChore(){
        ChoreService service = new ChoreService();
        service.addChore("Chore #01", LocalDate.now());
        assertFalse(service.getChores().get(0).getIsCompleted());
        assertDoesNotThrow(() -> service.toggleChore("Chore #01", LocalDate.now()));
        assertTrue(service.getChores().get(0).getIsCompleted());
        assertDoesNotThrow(() -> service.toggleChore("Chore #01", LocalDate.now()));
        assertFalse(service.getChores().get(0).getIsCompleted());
    }
    @Test
    @DisplayName("#toggleChore > When deadline is invalid and Chore is completed > throws exception")
    void toggleChoreWhenDeadlineIsInvalidAndChoreIsCompletedCannotToggle(){
        ChoreService service = new ChoreService();
        service.getChores().add(new Chore(Boolean.TRUE,"Chore #01" ,LocalDate.now().minusDays(1)));
        assertThrows(ToggleChoreWithInvalidDeadlineException.class
                ,()-> service.toggleChore("Chore #01", LocalDate.now().minusDays(1)));
    }
}
