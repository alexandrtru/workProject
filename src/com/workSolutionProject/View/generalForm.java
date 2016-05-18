package com.workSolutionProject.View;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 14.05.2016.
 */
public class GeneralForm extends JFrame{
    private final String title = "Work Solution";
    private final Dimension minSize = new Dimension(800, 500);
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTable devicesTableView;

    public GeneralForm() throws HeadlessException {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(minSize);
        setMaximumSize(minSize);
        setLocationRelativeTo(null);
        setJMenuBar(createMenu());
        setContentPane(panel1);
    }

    public void setDevicesTable(AbstractTableModel abstractTable) {
        devicesTableView.setModel(abstractTable);
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
