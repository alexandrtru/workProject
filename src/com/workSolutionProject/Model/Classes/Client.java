package com.workSolutionProject.Model.Classes;


import com.workSolutionProject.Exceptions.ModelExceptions.ClientExceptions.InvalidClientIdException;
import com.workSolutionProject.Exceptions.ModelExceptions.ClientExceptions.InvalidClientNameException;
import com.workSolutionProject.Helpers.IDatabaseRow;

import java.util.logging.Logger;

public class Client implements IDatabaseRow {
    private int id = 0;
    private String name;
    private String contactInfo;

    //GETTERS
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    //SETTERS
    public void setName(String name) throws InvalidClientNameException {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
        else {
            throw new InvalidClientNameException("Client name is null or empty");
        }
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    private void setId(int id) throws InvalidClientIdException {
        if (id > 0) {
            this.id = id;
        }
        else{
            throw new InvalidClientIdException("invalid client id record, we are get id " + id);
        }
    }

    //CONSTRUCTORS
    public Client(int id, String name, String contactInfo) throws InvalidClientIdException, InvalidClientNameException {//constructor for database selecting
        this.setId(id);
        this.setName(name);
        this.setContactInfo(contactInfo);
    }

    //INERFACE METHOD
    public boolean equalsById(IDatabaseRow databaseRecord){
        return databaseRecord.getId() == this.getId();
    }

    public boolean equalsById(Integer id){
        return id == this.getId();
    }

}
