package com.workSolutionProject.Model;

import com.workSolutionProject.Exceptions.ModelExceptions.ClientExceptions.InvalidClientIdException;
import com.workSolutionProject.Exceptions.ModelExceptions.ClientExceptions.InvalidClientNameException;
import com.workSolutionProject.Exceptions.ModelExceptions.ModelException;
import com.workSolutionProject.Model.Classes.Client;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.logging.Logger;

/**
 * Created by user on 01.05.2016.
 */
public class ClientTest {
    private Logger logger = Logger.getLogger("ClientTestLogger");

    @Test
    public void setIdPositive() throws Exception {
        Client c = new Client(1, "Bill", "8982");
        Assert.assertEquals(c.getId(), 1);
    }

    @Test(expected = InvalidClientIdException.class)
    public void setIdNull() throws Exception {
        Client c = new Client(0, "Bill", "8982");
    }

    @Test(expected = InvalidClientIdException.class)
    public void setIdNegative() throws Exception {
        Client c = new Client(-1, "Bill", "8982");
    }

    @Test
    public void setNameValid() throws ModelException {
        Client c = new Client(1, "Bill", "8982");
    }

    @Test(expected = InvalidClientNameException.class)
    public void setNameEmpty() throws ModelException {
            Client c = new Client(1, "", "8982");
    }

    @Test (expected = InvalidClientNameException.class)
    public void setNameNull() throws ModelException {
        Client c = new Client(1, null, "8982");
    }
}