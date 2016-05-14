package com.workSolutionProject.Exceptions.ModelExceptions.ClientExceptions;

import com.workSolutionProject.Exceptions.ModelExceptions.ModelException;
import com.workSolutionProject.Model.Model;

/**
 * Created by user on 01.05.2016.
 */
public class InvalidClientNameException extends ModelException {
    public InvalidClientNameException(String message) {
        super(message);
    }
}
