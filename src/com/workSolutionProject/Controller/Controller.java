package com.workSolutionProject.Controller;

import com.workSolutionProject.Helpers.DevicesAbstractTable;
import com.workSolutionProject.Model.Model;
import com.workSolutionProject.View.View;

/**
 * Created by user on 14.05.2016.
 */
public class Controller {
    Model model;
    View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        view.showGeneralForm();
        view.setDeviceAbstractTable(new DevicesAbstractTable(model.devicesList));
    }
}
