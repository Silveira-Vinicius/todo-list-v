import org.example.Exceptions.DuplicatedChoreException;
import org.example.Exceptions.InvalidDeadlineException;
import org.example.Exceptions.InvalidDescriptionException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChoreService {
    private List<Chore> chores;
    public ChoreService(){
        chores = new ArrayList<>();
    }
    public Chore addChore(String description, LocalDate deadline) {
        if (description == null || description.isEmpty()){
            throw new InvalidDescriptionException("Description Null or empty");
        }
        if (deadline == null || deadline.isBefore(LocalDate.now())){
            throw new InvalidDeadlineException("Date cannot be null or before today");
        }
        for (Chore chore:chores){
            if (chore.getDescription().equals(description)
                    && chore.getDeadline().isEqual(deadline)){
                throw new DuplicatedChoreException("Chore Already exists!");
            }
        }
        Chore chore = new Chore();
        chore.setDescription(description);
        chore.setDeadline(deadline);
        chore.setIsCompleted(Boolean.FALSE);
        chores.add(chore);
        return chore;
    }
}
