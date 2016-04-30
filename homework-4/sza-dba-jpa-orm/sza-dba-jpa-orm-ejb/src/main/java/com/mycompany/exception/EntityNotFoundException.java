package com.mycompany.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class EntityNotFoundException extends RuntimeException{
    
    public EntityNotFoundException(String msg) {
        super(msg);
    }

    public EntityNotFoundException() {
        super();
    }
}
