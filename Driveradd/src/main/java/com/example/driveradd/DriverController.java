package com.example.driveradd;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DriverController {
    @FXML
    private TextField nameTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private TextField salaryTextField;

    @FXML
    private Button addButton;
    @FXML
    private TextArea driversTextArea;

    private List<Driver> driversList = new ArrayList<>();

    @FXML
    public void addDriver() {
        String name = nameTextField.getText();
        int id = Integer.parseInt(idTextField.getText());
        int age = Integer.parseInt(ageTextField.getText());
        double salary = Double.parseDouble(salaryTextField.getText());

        Driver driver = new Driver(name, id, age, salary);
        driversList.add(driver);
        String driversString = "";
        for (Driver d : driversList) {
            driversString += d.toString() + "\n";
        }

        driversTextArea.setText(driversString);
    }
}
