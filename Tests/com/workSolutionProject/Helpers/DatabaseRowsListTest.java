package com.workSolutionProject.Helpers;

import com.workSolutionProject.Model.Classes.Client;
import com.workSolutionProject.Model.Classes.Device;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by Марина on 04.05.2016.
 */
public class DatabaseRowsListTest {
    private DatabaseRowsList<Device> devices = new DatabaseRowsList<Device>();
    private DatabaseRowsList<Client> clients = new DatabaseRowsList<Client>();

    @Test
    public void containsByDatabaseIdObjectNormal() throws Exception {
        Client c = new Client(1, "Bill", "8982");
        Device dev = new Device(1, "Cartrige", c, new Date(), null, false, "toner");
        devices.add(dev);
        Assert.assertEquals(devices.containsByDatabaseId(dev), dev);
    }

    @Test
    public void containsByDatabaseIdObjectFailed() throws Exception {
        Client c = new Client(1, "Bill", "8982");
        Device dev = new Device(1, "Cartrige", c, new Date(), null, false, "toner");
        Device dev2 = new Device(2, "Cartrige", c, new Date(), null, false, "toner");
        devices.add(dev);
        Assert.assertEquals(devices.containsByDatabaseId(dev2), null);
    }

    @Test
    public void containsByDatabaseIdInetgerNormal() throws Exception {
        Client c = new Client(1, "Bill", "8982");
        Device dev = new Device(1, "Cartrige", c, new Date(), null, false, "toner");
        devices.add(dev);
        Assert.assertEquals(devices.containsByDatabaseId(1), dev);
    }

    @Test
    public void containsByDatabaseIdInetgerInvalid() throws Exception {
        Client c = new Client(1, "Bill", "8982");
        Device dev = new Device(1, "Cartrige", c, new Date(), null, false, "toner");
        devices.add(dev);
        Assert.assertEquals(devices.containsByDatabaseId(0), null);
    }

    @Test
    public void containsByDatabaseIdInetgerClient() throws Exception {
        Client c = new Client(1, "Bill", "8982");
        Device dev = new Device(1, "Cartrige", c, new Date(), null, false, "toner");
        devices.add(dev);
        Assert.assertEquals(devices.containsByDatabaseId(0), null);
    }

}