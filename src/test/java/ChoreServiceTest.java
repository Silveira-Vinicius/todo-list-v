import org.example.Exceptions.DuplicatedChoreException;
import org.example.Exceptions.InvalidDeadlineException;
import org.example.Exceptions.InvalidDescriptionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChoreServiceTest {
    @Test
    @DisplayName("#addChore: > When description is invalid > Throws an exception")
    void addChoreWhenDescriptionIsInvalidThrowsAnException(){
        ChoreService service = new ChoreService();
        assertAll(
                () -> assertThrows(InvalidDescriptionException.class,
                    () -> service.addChore(null, null)),
                ()-> assertThrows(InvalidDescriptionException.class,
                    () -> service.addChore("", null)),
                () -> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore(null, LocalDate.now().plusDays(1))),
                ()-> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore("", LocalDate.now().plusDays(1))),
                () -> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore(null, LocalDate.now().minusDays(1))),
                ()-> assertThrows(InvalidDescriptionException.class,
                        () -> service.addChore("", LocalDate.now().minusDays(1)))
        );

    }

    @Test
    @DisplayName("#addChore: > When the deadline is invalid > Throws an Exception")
    void addChoreWhenDateIsInvalidThrowsAnException(){
        ChoreService service = new ChoreService();
        assertAll(
                () -> assertThrows(InvalidDeadlineException.class,
                        () -> service.addChore("Description", null)),
                () -> assertThrows(InvalidDeadlineException.class,
                        () -> service.addChore("Description", LocalDate.now().minusDays(1)))
        );
    }
    @Test
    @DisplayName("#addChore: > When the chore is repeated > Throws an Exception")
    void addChoreWhenChoreIsRepeatedThrowsAnException(){
        ChoreService service = new ChoreService();
        service.addChore("Description", LocalDate.now());
        assertThrows(DuplicatedChoreException.class,
                        () -> service.addChore("Description", LocalDate.now()));
    }

}
