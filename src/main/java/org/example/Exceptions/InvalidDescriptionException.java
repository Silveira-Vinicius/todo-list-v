package org.example.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidDescriptionException extends RuntimeException{
    public InvalidDescriptionException(String message){
        super(message);
    }

}
