package com.example.driveradd;

import java.io.*;
import java.util.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.control.TableView;

import javafx.scene.layout.*;

public class DriverController {
    @FXML
    private List<Driver> driverList = new ArrayList<>();
    @FXML
    private TableView<Driver> driverTable ;
    @FXML
    private TableColumn<Driver, String> nameColumn;
    @FXML
    private TableColumn<Driver, Integer> idColumn;
    @FXML
    private TableColumn<Driver, Integer> ageColumn;
    @FXML
    private TableColumn<Driver, Double> salaryColumn;
    @FXML
    private TableColumn<Driver, String> routeColumn;
    @FXML
    private TextField nameField;
    @FXML
    private TextField idField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField salaryField;
    @FXML
    private TextField routeField;

    private ObservableList<Driver> driverData = FXCollections.observableArrayList();

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        routeColumn.setCellValueFactory(new PropertyValueFactory<>("route"));
        loadDriverData();
    }

    @FXML
    private void handleAddDriverButton(ActionEvent event) {
        String name = nameField.getText();
        String id = idField.getText();
        int age = Integer.parseInt(ageField.getText());
        double salary = Double.parseDouble(salaryField.getText());
        String route = routeField.getText();
        Driver driver = new Driver(name, id, age, salary, route);
        driverList.add(driver);
        nameField.clear();
        idField.clear();
        ageField.clear();
        salaryField.clear();
        routeField.clear();
        updateDriverTable();
    }

    @FXML
    private void handleAddDriverButton(){
        String name = nameField.getText();
        String id = idField.getText();
        int age = Integer.parseInt(ageField.getText());
        double salary = Double.parseDouble(salaryField.getText());
        String route = routeField.getText();

        Driver newDriver = new Driver(name, id, age, salary, route);
        driverData.add(newDriver);
        saveDriverData();

        nameField.clear();
        idField.clear();
        ageField.clear();
        salaryField.clear();
        routeField.clear();
    }

    @FXML
    private void handleDeleteDriverButton(ActionEvent event) {
        int selectedIndex = driverTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            driverTable.getItems().remove(selectedIndex);
            saveDriverData();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Driver Selected");
            alert.setHeaderText("No Driver Selected");
            alert.setContentText("Please select a driver in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleChangeRouteButton(ActionEvent event) {
        int selectedIndex = driverTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Driver driver = driverTable.getItems().get(selectedIndex);
            TextInputDialog dialog = new TextInputDialog(driver.getRoute());
            dialog.setTitle("Change Route");
            dialog.setHeaderText("Change Route");
            dialog.setContentText("Please enter the new route:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String newRoute = result.get();
                driver.setRoute(newRoute);
                driverTable.refresh();
                saveDriverData();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Driver Selected");
            alert.setHeaderText("No Driver Selected");
            alert.setContentText("Please select a driver in the table.");
            alert.showAndWait();
        }
    }
    private void updateDriverTable() {
        ObservableList<Driver> driverData = FXCollections.observableArrayList(driverList);
        driverTable.setItems(driverData);
    }


    private void loadDriverData() {
        try {
            FileInputStream fileIn = new FileInputStream("H:\\\\Driveradd\\\\src\\\\main\\\\resources\\\\com\\\\example\\\\driveradd\\\\input.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<Driver> drivers = (List<Driver>) in.readObject();
            driverData.addAll(drivers);
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveDriverData() {
        try {
            FileOutputStream fileOut = new FileOutputStream("H:\\\\Driveradd\\\\src\\\\main\\\\resources\\\\com\\\\example\\\\driveradd\\\\input.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(new ArrayList<>(driverData));
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

