package com.example.store_management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TableColumn<?, ?> employees_col_data;

    @FXML
    private TableColumn<?, ?> employees_col_employeeID;

    @FXML
    private TableColumn<?, ?> employees_col_firstName;

    @FXML
    private TableColumn<?, ?> employees_col_gender;

    @FXML
    private TableColumn<?, ?> employees_col_lastName;

    @FXML
    private TableColumn<?, ?> employees_col_password;

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
    private TableView<?> employees_tableView;

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

    }
}
