package com.workSolutionProject.Helpers;

import com.workSolutionProject.Model.Classes.Device;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;

/**
 * Created by user on 14.05.2016.
 */
public class DevicesAbstractTable extends AbstractTableModel {
    private DatabaseRowsList<Device> devices;

    public DevicesAbstractTable(DatabaseRowsList devices) {
        this.devices = devices;
    }

    @Override
    public int getRowCount() {
        return devices.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public String getColumnName(int index) {
        switch (index){
            case 0: return "№";
            case 1: return "Устройство";
            case 2: return "Владелец";
            case 3: return "Контакты";
            case 4: return "Принят";
            case 5: return "Готов";
            case 6: return "Неисправность";
            case 7: return "Выдан";
            default: return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex){
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        switch (columnIndex){
            case 0: return devices.get(rowIndex).getId();
            case 1: return devices.get(rowIndex).getDeviceName();
            case 2: return devices.get(rowIndex).getOwner().getName();
            case 3: return devices.get(rowIndex).getOwner().getContactInfo();
            case 4: return dateFormat.format(devices.get(rowIndex).getDateIn());
            case 5: return devices.get(rowIndex).getComplete();
            case 6: return devices.get(rowIndex).getDefect();
            case 7: return devices.get(rowIndex).getDateOut() != null ? dateFormat.format(devices.get(rowIndex).getDateOut()) : "";
            default: return null;
        }
    }

}
