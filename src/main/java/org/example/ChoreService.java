package org.example;

import org.example.Exceptions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        //chores.remove(Chore);
        return chore;
    }

    public List<Chore> getChores() {return chores;}

    public void deleteChore (String description, LocalDate deadline) {
        if (isChoreListEmpty.test(this.chores)){
            throw new EmptyChoreListException("Unable to remove a chore from an empty list");
        }
        boolean isChoreExist = this.chores.stream().anyMatch(chore -> chore.getDescription().equals(description)
                && chore.getDeadline().isEqual(deadline) );
        if(!isChoreExist){
            throw new ChoreNotFoundException("The given chore does not exist");
        }
        this.chores = this.chores.stream().filter(chore -> !chore.getDescription().equals(description)
            && !chore.getDeadline().isEqual(deadline)).collect(Collectors.toList());

    }

    /**
     *
     * Methot to toogle a Chore
     *
     * @param  description The chore's description
     * @param deadline The deadline's description
     * @throws ChoreNotFoundException When the chore is not found on the list
     */
    public void toggleChore(String description, LocalDate deadline){
        boolean isChoreExist = this.chores.stream().anyMatch((chore) -> chore.getDescription().equals(description)
        && chore.getDeadline().equals(deadline));
        if (!isChoreExist){
            throw new ChoreNotFoundException();
        }
        this.chores.stream().map(chore->{
                if(!chore.getDescription().equals(description)&& !chore.getDeadline().isEqual(deadline)){
                    return chore;
                }
                if (chore.getDeadline().isBefore(LocalDate.now())
                && chore.getIsCompleted()){
                    throw new ToggleChoreWithInvalidDeadlineException("Cannot toggle a completed chore with invalid deadline");

                }
                chore.setIsCompleted(!chore.getIsCompleted());
                return chore;
        }).collect(Collectors.toList());
    }

    private final Predicate<List<Chore>> isChoreListEmpty = choreList -> choreList.isEmpty();
}
