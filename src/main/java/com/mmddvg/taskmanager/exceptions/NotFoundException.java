package com.mmddvg.taskmanager.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String entityName , Long id) {
        super(entityName + " with id " + id + " not found");
    }


    public NotFoundException(String entityName , UUID id) {
        super(entityName + " with id " + id + " not found");
    }

    public NotFoundException(String entityName , String pk) {
        super(entityName + " : " + pk + " not found");
    }
}
