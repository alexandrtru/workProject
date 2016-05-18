package com.workSolutionProject.Helpers.Events;

import java.util.EventObject;

/**
 * Created by Марина on 18.05.2016.
 */
public class DeviceOperationEvent extends EventObject {
     /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */

    private int deviceId;

    public DeviceOperationEvent(Object source, int deviceId) {
        super(source);
        this.deviceId = deviceId;
    }

    public int getDeviceId() {
        return deviceId;
    }

}
