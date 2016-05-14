package com.workSolutionProject.Helpers;

import com.workSolutionProject.Model.Classes.Client;
import com.workSolutionProject.Model.Classes.Device;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 03.05.2016.
 */
public class DataBaseWorkerTest {

    @Test
    public void getAllClientsFromDatabase() throws Exception {
        DatabaseWorker dataBaseWorker = new DatabaseWorker();
        List<Client> cl = dataBaseWorker.getAllClientsFromDatabase(); //Тут можно посмотреть содержимое при прохождении теста
        Assert.assertEquals(cl.isEmpty(),false);
    }

    //Тесты сетят в базу
   /*@Test
    public void insertingNewClient() throws Exception{
        DatabaseWorker dataBaseWorker = new DatabaseWorker();
        Client cl = dataBaseWorker.addClient("Горбунов Алексей", "8351613126");
        Assert.assertEquals(cl.getId(), 13);
    }

   @Test public void insertingNewDevice() throws Exception{
        DatabaseWorker dataBaseWorker = new DatabaseWorker();
        Client cl = new Client (7, "Горбунов Алексей", "8351613126");
        Device dev = dataBaseWorker.addDevice("cartrige", cl, "toner");
        Assert.assertEquals(dev.getId(), 13);
    }

    @Test
    public void updateClient() throws Exception{
        Client client = new Client(9, "Alexander", "89823212147");
        DatabaseWorker dbWorker = new DatabaseWorker();
        Assert.assertEquals(dbWorker.updateClient(client),true);
    }

    @Test
    public void updateDevice() throws Exception{
        Client client = new Client(9, "Alexander", "89823212147");
        Device dev = new Device(8, "Cartrige", client, new Date(), null, false, "toner");
        DatabaseWorker dbWorker = new DatabaseWorker();
        Assert.assertEquals(dbWorker.updateDevice(dev), true);
    }*/
}