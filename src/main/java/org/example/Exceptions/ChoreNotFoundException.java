package org.example.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ChoreNotFoundException extends RuntimeException{
    public ChoreNotFoundException(String message){
        super(message);
    }
}
