package com.example.store_management;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private AnchorPane admin_form;

    @FXML
    private Hyperlink admin_hyperLink;

    @FXML
    private Button admin_loginBtn;

    @FXML
    private PasswordField admin_password;

    @FXML
    private TextField admin_username;

    @FXML
    private Hyperlink employee_hyperLink;

    @FXML
    private TextField employee_id;

    @FXML
    private Button employee_loginBtn;

    @FXML
    private PasswordField employee_password;

    @FXML
    private AnchorPane employee_form;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button exit_Btn;

    private Connection connect;
    private ResultSet result;
    private PreparedStatement prepare;

    public void adminLogin() {
        String adminData = "SELECT * FROM admin WHERE username = ? and password = ?";

        connect = database.connectDb();

        try {
            Alert alert;

            if (admin_username.getText().isEmpty() || admin_password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Повідомлення про помилку");
                alert.setHeaderText(null);
                alert.setContentText("Будь ласка, заповніть усі порожні поля");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(adminData);
                prepare.setString(1, admin_username.getText());
                prepare.setString(2, admin_password.getText());

                result = prepare.executeQuery();

                if (result != null && result.next()) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Інформаційне повідомлення");
                    alert.setHeaderText(null);
                    alert.setContentText("Успішний вхід!");
                    alert.showAndWait();

                    admin_loginBtn.getScene().getWindow().hide();

                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminDashboard.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1100, 600);

                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Повідомлення про помилку");
                    alert.setHeaderText(null);
                    alert.setContentText("Неправильний логін/пароль");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (prepare != null) {
                    prepare.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public void switchForm(ActionEvent event) {
        if(event.getSource() == admin_hyperLink){
            admin_form.setVisible(false);
            employee_form.setVisible(true);
        }else if(event.getSource() == employee_hyperLink) {
            admin_form.setVisible(true);
            employee_form.setVisible(false);
        }
    }

    @FXML
    private void handleCloseButtonClick() {
        System.exit(0);
    }


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}