package com.example.store_management;

import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class employeeData {

    private String employee_id;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private String data;

    public employeeData(String employee_id, String password, String firstName, String lastName, String gender, String data){
        this.employee_id = employee_id;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.data = data;
    }

    public String getEmployee_id(){
        return employee_id;
    }
    public String getPassword(){
        return password;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getGender(){
        return gender;
    }
    public String getData(){
        return data;
    }
}
