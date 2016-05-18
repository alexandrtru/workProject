package com.workSolutionProject.View;

import com.workSolutionProject.Helpers.Events.DeviceOperationEvent;
import com.workSolutionProject.Helpers.Events.DeviceOperationListener;

import javax.swing.table.AbstractTableModel;

/**
 * Created by user on 14.05.2016.
 */
public class View {
    private GeneralForm generalForm = new GeneralForm();

    public void showGeneralForm(){
        generalForm.setVisible(true);
        generalForm.addDeviceOperationLisetner(new DeviceOperationListener(){
            @Override
            public void onDeviceOperation(DeviceOperationEvent ev) {
                new EditDeviceForm();
            }
        });
    }

    public void setDeviceAbstractTable(AbstractTableModel abstractTable){
        generalForm.setDevicesTable(abstractTable);
    }
}
