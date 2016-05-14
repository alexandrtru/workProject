package com.workSolutionProject.Сontroller;

import com.workSolutionProject.Model.Classes.Device;
import com.workSolutionProject.Model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.text.TableView;
import java.io.IOException;

public class GeneralSceneController {

    private Model model;
    @FXML
    private TableView table;
    private ObservableList<Device> observableList = FXCollections.observableList(model.devicesList);

    @FXML
    private TableColumn<Device, Integer> deviceId;

    public GeneralSceneController() throws Exception {
        model = new Model();
    }

    @FXML
    protected void initialize(){
        deviceId.setCellValueFactory(
                new PropertyValueFactory<Device,Integer>("deviceId")
        );


    }
}
