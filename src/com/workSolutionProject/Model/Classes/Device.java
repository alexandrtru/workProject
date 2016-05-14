package com.workSolutionProject.Model.Classes;

import com.workSolutionProject.Exceptions.ModelExceptions.DeviceExceptions.InvalidDeviceIdException;
import com.workSolutionProject.Exceptions.ModelExceptions.DeviceExceptions.InvalidDeviceNameException;
import com.workSolutionProject.Exceptions.ModelExceptions.DeviceExceptions.InvalidOwnerValueException;
import com.workSolutionProject.Helpers.IDatabaseRow;

import java.util.Date;
import java.util.logging.Logger;

public class Device implements IDatabaseRow {

    private int id = 0;
    private String deviceName;
    private Client owner;
    private Date dateIn;
    private Date dateOut;
    private boolean complete = false;
    private String defect;

    //GETTERS
    public int getId() {
        return id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public Client getOwner() {
        return owner;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public boolean getComplete() {
        return complete;
    }

    public String getDefect() {
        return defect;
    }

    //SETTERS
    public void setDeviceName(String deviceName) throws InvalidDeviceNameException {
        if (deviceName != null && !deviceName.trim().isEmpty()) {
            this.deviceName = deviceName;
        }
        else
        {
            throw new InvalidDeviceNameException("deviceName is null or empty");
        }
    }

    public void setOwner(Client owner) throws InvalidOwnerValueException {
       if(owner != null) {
           this.owner = owner;
       }
       else
       {
        throw new InvalidOwnerValueException("Owner is null.");
       }
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public void setComplete(boolean complete) {
        if (!this.complete == false && complete == true) {
            this.complete = complete;
            setDateOut(new Date());
        } else
        if (this.complete == true && complete == false) {
            setDateIn(new Date());
            setDateOut(null);
        }
    }

    public void setDefect(String defect) {
        this.defect = defect;
    }

    private void setId(int id) throws InvalidDeviceIdException {
        if (id > 0) {
            this.id = id;
        }
        else{
        throw new InvalidDeviceIdException("invalid device record id, we are get " + id);
        }
    }

    private void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    //CONSTRUCTORS
    public Device(int id, String device, Client owner, Date dateIn, Date dateOut, boolean complete, String defect) throws InvalidDeviceIdException, InvalidDeviceNameException, InvalidOwnerValueException {
        this.setId(id);
        this.setDeviceName(device);
        this.setOwner(owner);
        this.setDefect(defect);
        this.setComplete(complete);
        this.setDateIn(dateIn);
        this.setDateOut(dateOut);
    }

    public Device(int id, String device, Client owner, String defect) throws InvalidDeviceIdException, InvalidDeviceNameException, InvalidOwnerValueException {
        this.setId(id);
        this.setDeviceName(device);
        this.setOwner(owner);
        this.setDefect(defect);
        this.setComplete(false);
        this.setDateIn(new Date());
        this.setDateOut(null);
    }

    //INTERFACE METHOD
    public boolean equalsById(IDatabaseRow databaseRecord){
        return databaseRecord.getId() == this.getId();
    }

    public boolean equalsById(Integer id){
        return id == this.getId();
    }
}
