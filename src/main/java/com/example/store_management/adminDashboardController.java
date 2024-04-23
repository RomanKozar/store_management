package com.example.store_management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class adminDashboardController implements Initializable {

    @FXML
    private Button addProducts_addBtn;

    @FXML
    private TextField addProducts_brandName;

    @FXML
    private Button addProducts_btn;

    @FXML
    private Button addProducts_clearBtn;

    @FXML
    private TableColumn<productData, String> addProducts_col_customerName;

    @FXML
    private TableColumn<productData, String> addProducts_col_data;

    @FXML
    private TableColumn<productData, String> addProducts_col_orderID;

    @FXML
    private TableColumn<productData, String> addProducts_col_number;

    @FXML
    private TableColumn<productData, String> addProducts_col_productID;

    @FXML
    private TableColumn<productData, String> addProducts_col_productName;

    @FXML
    private TableColumn<productData, String> addProducts_col_status;
    @FXML
    private TableColumn<productData, String> addProducts_col_ID;

    @FXML
    private Button addProducts_deleteBtn;

    @FXML
    private AnchorPane addProducts_form;

    @FXML
    private TextField addProducts_number;

    @FXML
    private TextField addProducts_productID;

    @FXML
    private TextField addProducts_productName;

    @FXML
    private TextField addProducts_ID;

    @FXML
    private TextField addProducts_search;

    @FXML
    private ComboBox<?> addProducts_status;

    @FXML
    private TableView<productData> addProducts_tableView;

    @FXML
    private Button addProducts_updateBtn;

    @FXML
    private Button close;

    @FXML
    private Button addProducts_request1;

    @FXML
    private Button addProducts_request2;

    @FXML
    private Button addProducts_request3;

    @FXML
    private Button addProducts_request4;

    @FXML
    private Button addProducts_request5;

    @FXML
    private Label dashboard_activeEmployess;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AreaChart<?, ?> dashboard_chart;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_totalIncome;

    @FXML
    private Button employees_bts;

    @FXML
    private Button employees_clearBtn;

    @FXML
    private TableColumn<employeeData, String> employees_col_data;

    @FXML
    private TableColumn<employeeData, String> employees_col_employeeID;

    @FXML
    private TableColumn<employeeData, String> employees_col_firstName;

    @FXML
    private TableColumn<employeeData, String> employees_col_gender;

    @FXML
    private TableColumn<employeeData, String> employees_col_lastName;

    @FXML
    private TableColumn<employeeData, String> employees_col_password;

    @FXML
    private Button employees_deleteBtn;

    @FXML
    private TextField employees_employeeID;

    @FXML
    private TextField employees_firstName;

    @FXML
    private AnchorPane employees_form;

    @FXML
    private ComboBox<?> employees_gender;

    @FXML
    private TextField employees_lastName;

    @FXML
    private TextField employees_password;

    @FXML
    private Button employees_saveBtn;

    @FXML
    private TableView<employeeData> employees_tableView;

    @FXML
    private Button employees_updateBtn;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Label username;

    private double x= 0;
    private double y= 0;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public void addProductsAdd(){
        String insertProduct = "INSERT INTO product "
                + "(ID, productID, customerName, productName, status, number)"
                + "VALUES(?, ?,?,?,?,?)";

        connect = database.connectDb();

        try{
            Alert alert;

            if(addProducts_productID.getText().isEmpty()
                                || addProducts_ID.getText().isEmpty()
                                || addProducts_brandName.getText().isEmpty()
                                || addProducts_productName.getText().isEmpty()
                                || addProducts_status.getSelectionModel().getSelectedItem() == null
                                || addProducts_number.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Будь ласка, заповніть усі порожні поля");
                alert.showAndWait();
            }else {

                String check = "SELECT productID FROM product WHERE productID = '"
                +addProducts_productID.getText()+"'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if(result.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Product ID:" + addProducts_productID.getText() + " вже був вихід");
                    alert.showAndWait();
                }else {

                    prepare = connect.prepareStatement(insertProduct);
                    prepare.setString(1, addProducts_ID.getText());
                    prepare.setString(2, addProducts_productID.getText());
                    prepare.setString(3, addProducts_brandName.getText());
                    prepare.setString(4, addProducts_productName.getText());
                    prepare.setString(5, (String) addProducts_status.getSelectionModel().getSelectedItem());
                    prepare.setString(6, addProducts_number.getText());

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Інформаційне повідомлення");
                    alert.setHeaderText(null);
                    alert.setContentText("Успішно додано");
                    alert.showAndWait();

                    addProductsShowData();
                    addProductsClear();
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProductsDelete() {
        String deleteProduct = "DELETE FROM product WHERE productID = '" + addProducts_productID.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;

            if (addProducts_productID.getText().isEmpty()
                    || addProducts_ID.getText().isEmpty()
                    || addProducts_brandName.getText().isEmpty()
                    || addProducts_productName.getText().isEmpty()
                    || addProducts_status.getSelectionModel().getSelectedItem() == null
                    || addProducts_number.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Виберіть елемент для видалення");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Інформаційне повідомлення");
                alert.setHeaderText(null);
                alert.setContentText("Ви точно хочете видалити продукт з ID: " + addProducts_productID.getText() + "?");

                Optional<ButtonType> optional = alert.showAndWait();
                if (optional.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(deleteProduct);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Інформаційне повідомлення");
                    alert.setHeaderText(null);
                    alert.setContentText("Успішно видалено");
                    alert.showAndWait();

                    addProductsShowData();
                    addProductsClear();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProductsClear(){
        addProducts_ID.setText("");
        addProducts_productID.setText("");
        addProducts_brandName.setText("");
        addProducts_productName.setText("");
        addProducts_status.getSelectionModel().clearSelection();
        addProducts_number.setText("");
    }

    @FXML
    public void handleRequest1Action(ActionEvent event) {
        String query = "SELECT customerName, AVG(CAST(number AS UNSIGNED)) AS averageNumber FROM product GROUP BY customerName";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            StringBuilder resultText = new StringBuilder("Середнє значення:\n");
            while (result.next()) {
                String customerName = result.getString("customerName");
                double averageNumber = result.getDouble("averageNumber");
                resultText.append(customerName).append(": ").append(averageNumber).append("\n");
            }
            showAlert(Alert.AlertType.INFORMATION, "Результат запиту", resultText.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }

    @FXML
    public void handleRequest2Action(ActionEvent event) {
        String query = "SELECT status, SUM(CAST(number AS UNSIGNED)) AS totalNumber FROM product GROUP BY status";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            StringBuilder resultText = new StringBuilder("Загальна кількість по статусу:\n");
            while (result.next()) {
                String status = result.getString("status");
                int totalNumber = result.getInt("totalNumber");
                resultText.append(status).append(": ").append(totalNumber).append("\n");
            }
            showAlert(Alert.AlertType.INFORMATION, "Результат запиту", resultText.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }

    @FXML
    public void handleRequest3Action(ActionEvent event) {
        String query = "SELECT MAX(CAST(number AS UNSIGNED)) AS maxNumber FROM product";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            if (result.next()) {
                int maxNumber = result.getInt("maxNumber");
                showAlert(Alert.AlertType.INFORMATION, "Результат запиту", "Максимальна кількість продуктів в одному замовленні: " + maxNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }

    @FXML
    void handleRequest4Action(ActionEvent event) {
        String query = "SELECT COUNT(DISTINCT productName) AS uniqueProducts FROM product";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            if (result.next()) {
                int uniqueProducts = result.getInt("uniqueProducts");
                showAlert(Alert.AlertType.INFORMATION, "Результат запиту", "Унікальних назв продуктів: " + uniqueProducts);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }

    @FXML
    public void handleRequest5Action(ActionEvent event) {
        String query = "SELECT customerName, COUNT(*) AS productsInProcess FROM product WHERE status = 'В обробці' GROUP BY customerName";
        connect = database.connectDb();
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                String customerName = result.getString("customerName");
                int productsInProcess = result.getInt("productsInProcess");
                resultMessage.append("Клієнт: ").append(customerName).append(", Продуктів в обробці: ").append(productsInProcess).append("\n");
            }
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    private String [] statusList = {
            "В обробці",
            "Завершено"
    };
    public void addProductsStatusList(){
        List<String> list = new ArrayList<>();

        for(String data : statusList){
            list.add(data);
        }

        ObservableList statusData = FXCollections.observableArrayList(list);
        addProducts_status.setItems(statusData);
    }

    public void addProductsSearch (){

        FilteredList<productData> filter = new FilteredList<>(addProductsList, e -> true);

        addProducts_search.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateProductData -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if(predicateProductData.getID().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateProductData.getProductID().toLowerCase().contains(searchKey)){
                    return true;
                } else if (predicateProductData.getCustomerName().toLowerCase().contains(searchKey)) {
                    return true;
                }else if(predicateProductData.getStatus().toLowerCase().contains(searchKey)){
                    return true;
                }else if (predicateProductData.getProductName().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateProductData.getNumber().toLowerCase().contains(searchKey)){
                    return true;
                }else return false;
            });
        });

        SortedList<productData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(addProducts_tableView.comparatorProperty());
        addProducts_tableView.setItems(sortList);
    }

    public ObservableList<productData> addProductListData(){
        ObservableList<productData> prodList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM product";

        connect = database.connectDb();

        try{
            productData prod;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()){
                prod = new productData(
                        result.getString("ID"),
                        result.getString("productID"),
                        result.getString("customerName"),
                        result.getString("productName"),
                        result.getString("status"),
                        result.getString("number"));


                prodList.add(prod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prodList;
    }


    private ObservableList<productData> addProductsList;

    public void addProductsShowData(){
        addProductsList = addProductListData();

        addProducts_col_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        addProducts_col_productID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        addProducts_col_customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addProducts_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        addProducts_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        addProducts_col_number.setCellValueFactory(new PropertyValueFactory<>("number"));

        addProducts_tableView.setItems(addProductsList);

    }

    public void addProductsSelect(){
        productData prod = addProducts_tableView.getSelectionModel().getSelectedItem();
        int num = addProducts_tableView.getSelectionModel().getSelectedIndex();

        if((num - 1) < 1){
            return;
        }

        addProducts_ID.setText(prod.getID());
        addProducts_productID.setText(prod.getProductID());
        addProducts_brandName.setText(prod.getCustomerName());
        addProducts_productName.setText(prod.getProductName());
        addProducts_number.setText(prod.getNumber());
    }

    public void employeesSave(){
        // Формування SQL запиту для вставки даних
        String insertEmployee = "INSERT INTO employee"
                + "(employee_id, password, firstName, lastName, gender, data) "
                + "VALUES(?,?,?,?,?,?)";

        // Встановлення з'єднання з базою даних
        connect = database.connectDb();

        try{
            // Перевірка на заповненість полів
            if (employees_employeeID.getText().isEmpty()
                    || employees_password.getText().isEmpty()
                    || employees_firstName.getText().isEmpty()
                    || employees_lastName.getText().isEmpty()
                    || employees_gender.getSelectionModel().getSelectedItem() == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Заповніть пусті поля");
            } else {
                // Підготовка запиту
                prepare = connect.prepareStatement(insertEmployee);
                prepare.setString(1, employees_employeeID.getText());
                prepare.setString(2, employees_password.getText());
                prepare.setString(3, employees_firstName.getText());
                prepare.setString(4, employees_lastName.getText());
                prepare.setString(5, (String) employees_gender.getSelectionModel().getSelectedItem());

                // Отримання поточної дати
                java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                // Встановлення дати в запит
                prepare.setDate(6, currentDate);

                // Виконання запиту
                prepare.executeUpdate();
                showAlert(Alert.AlertType.INFORMATION, "Інформаційне повідомлення", "Ви сохранили");

                // Оновлення даних на формі
                employeesShowListData();
                employeesReset();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String [] genderList = {"Чоловік", "Жінка"};
    public void employeesGender(){
        List<String> genderL = new ArrayList<>();

        for(String data: genderList){
            genderL.add(data);
        }

        ObservableList listG = FXCollections.observableArrayList(genderL);
        employees_gender.setItems(listG);
    }

    public void employeeDelete(){

        String deleteEmployee = "DELETE FROM employee WHERE employee_id = '"
                +employees_employeeID.getText()+"'";

        connect = database.connectDb();

        try{
            Alert alert;

            if (employees_employeeID.getText().isEmpty()
                    || employees_password.getText().isEmpty()
                    || employees_firstName.getText().isEmpty()
                    || employees_lastName.getText().isEmpty()
                    || employees_gender.getSelectionModel().getSelectedItem() == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Заповніть пусті поля");
            } else{
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Інформаційне повідомлення");
                alert.setHeaderText(null);
                alert.setContentText("Ви точно хочете видалити продукт з ID: " + employees_employeeID.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    statement = connect.createStatement();
                    statement.executeUpdate(deleteEmployee);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Інформаційне повідомлення");
                    alert.setHeaderText(null);
                    alert.setContentText("Успішно видалено");
                    alert.showAndWait();

                    employeesShowListData();
                    employeesReset();
                }else return;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void employeesReset(){
        employees_employeeID.setText("");
        employees_password.setText("");
        employees_firstName.setText("");
        employees_lastName.setText("");
        employees_gender.getSelectionModel().clearSelection();
    }

    public ObservableList<employeeData> employeesListData(){

        ObservableList<employeeData> emData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM employee";

        connect = database.connectDb();

        try{
            employeeData employeeD;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()){

                employeeD = new employeeData(
                        result.getString("employee_id"),
                        result.getString("password"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("gender"),
                        result.getString("data"));

                emData.add(employeeD);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return emData;
    }

    private ObservableList<employeeData> employeesList;
    public void employeesShowListData(){
        employeesList = employeesListData();

        employees_col_employeeID.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        employees_col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        employees_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        employees_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        employees_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        employees_col_data.setCellValueFactory(new PropertyValueFactory<>("data"));

        employees_tableView.setItems(employeesList);

    }

    public void employeesSelect(){
        employeeData employeeD = employees_tableView.getSelectionModel().getSelectedItem();
        int num = employees_tableView.getSelectionModel().getSelectedIndex();

        if((num-1) < 1){
            return;
        }

        employees_employeeID.setText(employeeD.getEmployee_id());
        employees_password.setText(employeeD.getPassword());
        employees_firstName.setText(employeeD.getFirstName());
        employees_lastName.setText(employeeD.getLastName());
    }

    public void logout() {
        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Повідомлення підтвердження");
            alert.setHeaderText(null);
            alert.setContentText("Ви впевнені, що бажаєте вийти");

            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)){

                logout.getScene().getWindow().hide();

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminSignIn.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);

                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);

                scene.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                scene.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                scene.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.setScene(scene);
                stage.show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchForm(ActionEvent event) {

        if(event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            addProducts_form.setVisible(false);
            employees_form.setVisible(false);
        } else if (event.getSource() == addProducts_btn) {
            dashboard_form.setVisible(false);
            addProducts_form.setVisible(true);
            employees_form.setVisible(false);

            addProductsShowData();
            addProductsStatusList();
            addProductsSearch();

        } else if (event.getSource() == employees_bts) {
            dashboard_form.setVisible(false);
            addProducts_form.setVisible(false);
            employees_form.setVisible(true);


        }

    }

    public void close(){
        System.exit(0);
    }

    public void minimize(){
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            addProductsShowData();
            addProductsStatusList();

            employeesShowListData();
            employeesGender();

    }
}
