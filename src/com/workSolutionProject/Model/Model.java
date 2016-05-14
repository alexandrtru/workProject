package com.workSolutionProject.Model;

import com.workSolutionProject.Exceptions.ModelExceptions.ModelException;
import com.workSolutionProject.Helpers.DatabaseRowsList;
import com.workSolutionProject.Helpers.DatabaseWorker;
import com.workSolutionProject.Model.Classes.Client;
import com.workSolutionProject.Model.Classes.Device;

import java.util.logging.Logger;

/**
 * Created by user on 03.05.2016.
 */

public class Model {
    public DatabaseRowsList<Device> devicesList = new DatabaseRowsList<Device>();
    public DatabaseRowsList<Client> clientsList = new DatabaseRowsList<Client>();

    private DatabaseWorker databaseWorker;
    private Logger logger = Logger.getLogger("ModelLogger");

    //CONSTRUCTOR
    public Model() throws Exception {
        this.databaseWorker = new DatabaseWorker();
        clientsList.addAll(databaseWorker.getAllClientsFromDatabase());
        if(!clientsList.isEmpty()) devicesList.addAll(databaseWorker.getAllDevicesFromDatabase(clientsList));
    }

    //TODO addClient, addDevice, updateClient, updateClient, deleteClient, deleteDevice
    public void addClient(String name, String contactInfo){
        try {
            clientsList.add(databaseWorker.addClient(name, contactInfo));
        } catch (ModelException ex){
            logger.warning("ERROR IN addClient()! Message \n" + ex.getMessage());
        }
    }

    public void addDevice(String device, Client owner, String defect){
        try {
            devicesList.add(databaseWorker.addDevice(device, owner, defect));
        } catch (ModelException ex){
            logger.warning("ERROR IN addClient()! Message \n" + ex.getMessage());
        }
    }

    public void addDevice(String ownerName, String ownerContactInfo,String device, Client owner, String defect){
        try {
            Client newClient = databaseWorker.addClient(ownerName, ownerContactInfo);
            clientsList.add(newClient);
            devicesList.add(databaseWorker.addDevice(device, newClient, defect));
        } catch (ModelException ex){
            logger.warning("ERROR IN addClient()! Message \n" + ex.getMessage());
        }
    }

    public void updateClient(Client client, String newName, String newContactInfo){
        try {
            client.setName(newName);
            client.setContactInfo(newContactInfo);
            databaseWorker.updateClient(client);
        } catch (ModelException ex)
        {
            logger.warning("ERROR IN addClient()! Message \n" + ex.getMessage());
        }
    }

    public void updateDevice(Device device, String newDeviceName, String defect, boolean complete){
        try {
            device.setDeviceName(newDeviceName);
            device.setDefect(defect);
            device.setComplete(complete);
            databaseWorker.updateDevice(device);
        } catch (ModelException ex)
        {
            logger.warning("ERROR IN addClient()! Message \n" + ex.getMessage());
        }
    }

    public void deleteDevice(Device device){
        if ( databaseWorker.deleteDevice(device)) devicesList.remove(device);
    }

    public void deleteClient(Client client){
        if (databaseWorker.deleteClient(client)){
            for (Device device : devicesList)
            {
                if (device.getOwner() == client) devicesList.remove(device);
            }
            clientsList.remove(client);
        }
    }
}
