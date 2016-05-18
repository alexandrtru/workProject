package com.workSolutionProject.View;

import javax.swing.table.AbstractTableModel;

/**
 * Created by user on 14.05.2016.
 */
public class View {
    private GeneralForm generalForm = new GeneralForm();

    public void showGeneralForm(){
        generalForm.setVisible(true);
    }

    public void setDeviceAbstractTable(AbstractTableModel abstractTable){
        generalForm.setDevicesTable(abstractTable);
    }
}
