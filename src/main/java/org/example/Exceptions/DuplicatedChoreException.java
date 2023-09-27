package org.example.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DuplicatedChoreException extends RuntimeException{
    public DuplicatedChoreException(String message){
        super(message);
    }
}
