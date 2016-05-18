package com.workSolutionProject.View;

import com.workSolutionProject.Helpers.Events.DeviceOperationEvent;
import com.workSolutionProject.Helpers.Events.IDeviceOperationListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by user on 14.05.2016.
 */
public class GeneralForm extends JFrame {
    private final String title = "Work Solution";
    private final Dimension minSize = new Dimension(800, 500);
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTable devicesTableView;
    private JButton addDeviceButton;
    private JButton editDeviceButton;
    private JButton deleteDeviceButton;
    private ArrayList<IDeviceOperationListener> deviceOperationListenerList = new ArrayList<IDeviceOperationListener>();

    public GeneralForm() throws HeadlessException {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(minSize);
        setMaximumSize(minSize);
        setLocationRelativeTo(null);
        setJMenuBar(createMenu());
        setContentPane(panel1);
        addDeviceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int devId = devicesTableView.getSelectedRowCount() > 0 ? (Integer)devicesTableView.getValueAt(devicesTableView.getSelectedRow(), 0) : 0;
                fireDeviceOperation(devId);
            }
        });
    }

    public void setDevicesTable(AbstractTableModel abstractTable) {
        devicesTableView.setModel(abstractTable);
        devicesTableView.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                editDeviceButton.setEnabled(devicesTableView.getSelectedRowCount()>0 ? true : false);
                deleteDeviceButton.setEnabled(devicesTableView.getSelectedRowCount()>0 ? true : false);
            }
        });
    }

    public void addDeviceOperationLisetner(IDeviceOperationListener listener){
        deviceOperationListenerList.add(listener);
    }

    public void removeDeviceOperationListener(IDeviceOperationListener listener){
        deviceOperationListenerList.remove(listener);
    }

    private void fireDeviceOperation(int deviceId){
        DeviceOperationEvent event = new DeviceOperationEvent(this, deviceId);
        for (IDeviceOperationListener listener: deviceOperationListenerList )
        {
            listener.onDeviceOperation(event);
        }
    }

    private JMenuBar createMenu() {
        JMenuBar menu = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(menuItem);
        menu.add(fileMenu);
        return menu;
    }
}
