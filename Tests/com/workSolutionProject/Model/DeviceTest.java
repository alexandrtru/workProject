package com.workSolutionProject.Model;

import com.workSolutionProject.Exceptions.ModelExceptions.ClientExceptions.InvalidClientIdException;
import com.workSolutionProject.Exceptions.ModelExceptions.ClientExceptions.InvalidClientNameException;
import com.workSolutionProject.Exceptions.ModelExceptions.DeviceExceptions.InvalidDeviceIdException;
import com.workSolutionProject.Exceptions.ModelExceptions.DeviceExceptions.InvalidDeviceNameException;
import com.workSolutionProject.Exceptions.ModelExceptions.DeviceExceptions.InvalidOwnerValueException;
import com.workSolutionProject.Model.Classes.Client;
import com.workSolutionProject.Model.Classes.Device;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by user on 01.05.2016.
 */
public class DeviceTest {

    Client client;

    public DeviceTest() throws InvalidClientNameException, InvalidClientIdException {
        client = new Client(1, "Bill", "89823212147");
    }

    @Test
    public void setIdDeviceNormal() throws Exception {
        Device dev = new Device(1, "Cartrige", client, new Date(), null, false, "toner");
        Assert.assertEquals(dev.getId(), 1);
    }

    @Test(expected = InvalidDeviceIdException.class)
    public void setIdDeviceZero() throws Exception {
        Device dev = new Device(0, "Cartrige", client, new Date(), null, false, "toner");
    }

    @Test(expected = InvalidDeviceIdException.class)
    public void setIdDeviceNegative() throws Exception {
        Device dev = new Device(-1, "Cartrige", client, new Date(), null, false, "toner");
    }

    @Test
    public void setDeviceNameNormal() throws Exception {
        Device dev = new Device(1, "Cartrige", client, new Date(), null, false, "toner");
        Assert.assertEquals(dev.getDeviceName(), "Cartrige");
    }

    @Test(expected = InvalidDeviceNameException.class)
    public void setDeviceNameEmpty() throws Exception {
        Device dev = new Device(1, "", client, new Date(), null, false, "toner");
    }

    @Test(expected = InvalidDeviceNameException.class)
    public void setDeviceNameNull() throws Exception {
        Device dev = new Device(1, null, client, new Date(), null, false, "toner");
    }

    @Test
    public void setOwnerNormal() throws Exception {
        Device dev = new Device(1, "Cartrige", client, new Date(), null, false, "toner");
        Assert.assertEquals(dev.getOwner(), client);
    }

    @Test(expected = InvalidOwnerValueException.class)
    public void setOwnerNull() throws Exception {
        Device dev = new Device(1, "Cartrige", null, new Date(), null, false, "toner");
        Assert.assertEquals(dev.getOwner(), null);
    }
}