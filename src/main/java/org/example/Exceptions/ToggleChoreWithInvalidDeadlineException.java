package org.example.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ToggleChoreWithInvalidDeadlineException extends RuntimeException{

    public ToggleChoreWithInvalidDeadlineException(String message){
        super(message);
    }
}
