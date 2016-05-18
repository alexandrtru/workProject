package com.workSolutionProject;

import com.workSolutionProject.Controller.Controller;
import com.workSolutionProject.Model.Model;
import com.workSolutionProject.View.GeneralForm;
import com.workSolutionProject.View.View;

/**
 * Created by user on 08.05.2016.
 */
public class WorkSoltionProject {

    public static void main(String[] args) throws Exception {
        Controller controller = new Controller(new Model(), new View());
    }
}
