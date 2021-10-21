package com.example.week2.controllers;

import com.example.week2.Utilities.DBUtility;
import com.example.week2.Utilities.SceneChanger;
import com.example.week2.models.Camera;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TableViewController implements Initializable {

    @FXML
    private TableView<Camera> tableView;

    @FXML
    private TableColumn<Camera, Integer> idColumn;

    @FXML
    private TableColumn<Camera, String> makeColumn;

    @FXML
    private TableColumn<Camera, String> modelColumn;

    @FXML
    private TableColumn<Camera, Double> mpColumn;

    @FXML
    private TableColumn<Camera, Double> priceColumn;

    @FXML
    private TableColumn<Camera, Boolean> mirrorlessColumn;

    @FXML
    private TableColumn<Camera, Integer> unitsSoldColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("cameraId"));
        makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        unitsSoldColumn.setCellValueFactory(new PropertyValueFactory<>("unitsSold"));
        mirrorlessColumn.setCellValueFactory(new PropertyValueFactory<>("mirrorless"));
        mpColumn.setCellValueFactory(new PropertyValueFactory<>("megaPixels"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Populate table view
        tableView.getItems().addAll(DBUtility.getCameraDetails());
    }

    @FXML
    private void createNewCamera(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "create-camera-view.fxml", "Creaet Camera");
    }
}

