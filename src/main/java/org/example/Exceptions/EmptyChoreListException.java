package org.example.Exceptions;

import lombok.NoArgsConstructor;
@NoArgsConstructor
public class EmptyChoreListException extends RuntimeException{

    public EmptyChoreListException(String message){
            super(message);
    }


}
