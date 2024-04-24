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
import javafx.scene.layout.Region;
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
    private Button goods_btn;
    @FXML
    private Button deliveries_btn;
    @FXML
    private Button suppliers_btn;
    @FXML
    private Button inventory_btn;

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
    private TableView<goodsData> goods_tableView;

    @FXML
    private TableColumn<goodsData, String> goods_col_productID;
    @FXML
    private TableColumn<goodsData, String> goods_col_name;
    @FXML
    private TableColumn<goodsData, String> goods_col_companyName;
    @FXML
    private TableColumn<goodsData, String> goods_col_customerAddress;
    @FXML
    private TableColumn<goodsData, String> goods_col_category;
    @FXML
    private TableColumn<goodsData, String> goods_col_price;
    @FXML
    private TableColumn<goodsData, String> goods_col_quantityInStock;
    @FXML
    private TableColumn<goodsData, String> goods_col_data;

    @FXML
    private TableView<deliveriesData> deliveries_tableView;
    @FXML
    private TableColumn<deliveriesData, String> deliveries_col_deliveriesID;
    @FXML
    private TableColumn<deliveriesData, String> deliveries_col_productID;
    @FXML
    private TableColumn<deliveriesData, String> deliveries_col_number;
    @FXML
    private TableColumn<deliveriesData, String> deliveries_col_category;
    @FXML
    private TableColumn<deliveriesData, String> deliveries_col_deliveryDate;
    @FXML
    private TableColumn<deliveriesData, String> deliveries_col_priceOneProduct;
    @FXML
    private TableView<suppliersData> suppliers_tableView;
    @FXML
    private TableColumn<suppliersData, String> suppliers_col_vendorID;
    @FXML
    private TableColumn<suppliersData, String> suppliers_col_nameOfcompanyOrsupplier;
    @FXML
    private TableColumn<suppliersData, String> suppliers_col_contactInformation;
    @FXML
    private TableColumn<suppliersData, String> suppliers_col_location;
    @FXML
    private TableView<inventoryData> inventory_tableView;
    @FXML
    private TableColumn<inventoryData, String> inventory_col_inventoryID;
    @FXML
    private TableColumn<inventoryData, String> inventory_col_data;
    @FXML
    private TableColumn<inventoryData, String> inventory_col_product;

    @FXML
    private Button addProducts_deleteBtn;

    @FXML
    private AnchorPane addProducts_form;
    @FXML
    private AnchorPane goods_form;
    @FXML
    private AnchorPane deliveries_form;
    @FXML
    private AnchorPane suppliers_form;
    @FXML
    private AnchorPane inventory_form;

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
    @FXML
    public void Request1ActionEmployee(ActionEvent event) {
        // Змінюємо запит на обчислення середньої довжини employee_id
        String query = "SELECT AVG(CHAR_LENGTH(employee_id)) AS averageEmployeeIdLength FROM employee";
        connect = database.connectDb();
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            if (result.next()) {
                double averageEmployeeIdLength = result.getDouble("averageEmployeeIdLength");
                resultMessage.append("Середня довжина employee_id: ").append(averageEmployeeIdLength);
            }
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }

    @FXML
    public void Request2ActionEmployee(ActionEvent event) {
        // Запит для визначення середньої довжини імені співробітників
        String query = "SELECT AVG(LENGTH(firstName)) AS averageNameLength FROM employee";
        connect = database.connectDb();
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            if (result.next()) {
                double averageNameLength = result.getDouble("averageNameLength");
                resultMessage.append("Середня довжина імені співробітників: ").append(String.format("%.2f", averageNameLength));
            }
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }

    @FXML
    public void Request3ActionEmployee(ActionEvent event) {
        // Запит для визначення середньої довжини прізвища співробітників
        String query = "SELECT AVG(LENGTH(lastName)) AS averageLastNameLength FROM employee";
        connect = database.connectDb();
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            if (result.next()) {
                double averageLastNameLength = result.getDouble("averageLastNameLength");
                resultMessage.append("Середня довжина прізвища співробітників: ").append(String.format("%.2f", averageLastNameLength));
            }
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }

    @FXML
    public void Request4ActionEmployee(ActionEvent event) {
        // Запит для визначення кількості унікальних імен серед співробітників
        String query = "SELECT COUNT(DISTINCT firstName) AS uniqueFirstNames FROM employee";
        connect = database.connectDb();
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            if (result.next()) {
                int uniqueFirstNames = result.getInt("uniqueFirstNames");
                resultMessage.append("Кількість унікальних імен серед співробітників: ").append(uniqueFirstNames);
            }
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }

    @FXML
    public void Request5ActionEmployee(ActionEvent event) {
        // Запит для визначення кількості співробітників кожної статі
        String query = "SELECT gender, COUNT(*) AS count FROM employee GROUP BY gender";
        connect = database.connectDb();
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                String gender = result.getString("gender");
                int count = result.getInt("count");
                resultMessage.append("Стать: ").append(gender).append(", Кількість співробітників: ").append(count).append("\n");
            }
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestGoods1Action(ActionEvent event) {
        // Updated query to select the category and count of total products from the goods table
        String query = "SELECT category, COUNT(*) AS total_products FROM goods GROUP BY category ORDER BY total_products DESC";
        connect = database.connectDb();
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                String category = result.getString("category");
                int totalProducts = result.getInt("total_products");
                resultMessage.append("Категорія: ").append(category).append(", Загальна кількість продуктів: ").append(totalProducts).append("\n");
            }
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestGoods2Action(ActionEvent event) {
        // Updated query to select the category and average price of products from the goods table where quantity in stock is greater than 0
        String query = "SELECT category, AVG(price) AS average_price FROM goods WHERE quantity_in_stock > 0 GROUP BY category";
        connect = database.connectDb();
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                String category = result.getString("category");
                double averagePrice = result.getDouble("average_price"); // Use getDouble for average price
                resultMessage.append("Категорія: ").append(category).append(", Середня ціна: ").append(String.format("%.2f", averagePrice)).append("\n"); // Format the average price to two decimal places
            }
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestGoods3Action(ActionEvent event) { // Змінено назву методу на handleRequestGoods2Action
        // Updated query to select the company name and count of products supplied from the goods table, having more than 1 product supplied, ordered by the count of products supplied in descending order
        String query = "SELECT company_name, COUNT(*) AS products_supplied FROM goods GROUP BY company_name HAVING products_supplied > 1 ORDER BY products_supplied DESC";
        connect = database.connectDb();
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                String companyName = result.getString("company_name");
                int productsSupplied = result.getInt("products_supplied");
                resultMessage.append("Назва компанії: ").append(companyName).append(", Кількість поставлених товарів: ").append(productsSupplied).append("\n");
            }
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestGoods4Action(ActionEvent event) {
        // Updated query to select goods with a price higher than the average price in their category
        String query = "SELECT g1.* FROM goods g1 JOIN ( SELECT category, AVG(price) AS avg_price FROM goods GROUP BY category ) g2 ON g1.category = g2.category WHERE g1.price > g2.avg_price";
        connect = database.connectDb();
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                // Assuming 'id', 'name', 'category', 'price', and 'quantity_in_stock' are columns in your goods table
                int id = result.getInt("productID");
                String name = result.getString("name");
                String category = result.getString("category");
                double price = result.getDouble("price");
                int quantityInStock = result.getInt("quantity_in_stock");
                resultMessage.append("ID: ").append(id)
                        .append(", Назва: ").append(name)
                        .append(", Категорія: ").append(category)
                        .append(", Ціна: ").append(String.format("%.2f", price))
                        .append(", Кількість на складі: ").append(quantityInStock)
                        .append("\n");
            }
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestGoods5Action(ActionEvent event) {
        // Updated query to select goods where data is before '2023-03-13' and quantity_in_stock is greater than 1
        String query = "SELECT * FROM goods WHERE data < '2023-03-13' AND quantity_in_stock > 1";
        connect = database.connectDb();
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                // Assuming 'id', 'name', 'category', 'price', 'quantity_in_stock', and 'data' are columns in your goods table
                int id = result.getInt("productID");
                String name = result.getString("name");
                String category = result.getString("category");
                double price = result.getDouble("price");
                int quantityInStock = result.getInt("quantity_in_stock");
                String data = result.getString("data"); // Assuming 'data' is the column name for the date
                resultMessage.append("ID: ").append(id)
                        .append(", Назва: ").append(name)
                        .append(", Категорія: ").append(category)
                        .append(", Ціна: ").append(String.format("%.2f", price))
                        .append(", Кількість на складі: ").append(quantityInStock)
                        .append(", Дата: ").append(data)
                        .append("\n");
            }
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestGoods6Action(ActionEvent event) {
        // Updated query to select goods with quantity_in_stock less than 10 and order by quantity_in_stock in ascending order
        String query = "SELECT productID, name, category, quantity_in_stock FROM goods WHERE quantity_in_stock < 10 ORDER BY quantity_in_stock ASC";
        connect = database.connectDb();
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                // Assuming 'productID', 'name', 'category', and 'quantity_in_stock' are columns in your goods table
                int productID = result.getInt("productID");
                String name = result.getString("name");
                String category = result.getString("category");
                int quantityInStock = result.getInt("quantity_in_stock");
                resultMessage.append("ID продукту: ").append(productID)
                        .append(", Назва: ").append(name)
                        .append(", Категорія: ").append(category)
                        .append(", Кількість на складі: ").append(quantityInStock)
                        .append("\n");
            }
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestGoods7Action(ActionEvent event) {
        // Updated query to select categories and the sum of quantity_in_stock for each category, ordered by the total quantity in stock in descending order
        String query = "SELECT category, SUM(quantity_in_stock) AS total_quantity_in_stock FROM goods GROUP BY category ORDER BY total_quantity_in_stock DESC";
        connect = database.connectDb();
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                // Assuming 'category' and 'total_quantity_in_stock' are the columns in the result set
                String category = result.getString("category");
                int totalQuantityInStock = result.getInt("total_quantity_in_stock");
                resultMessage.append("Категорія: ").append(category)
                        .append(", Загальна кількість на складі: ").append(totalQuantityInStock)
                        .append("\n");
            }
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestDeliveries1Action(ActionEvent event) {
        String query = "SELECT category, COUNT(*) AS total_deliveries FROM deliveries GROUP BY category";
        connect = database.connectDb();
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                // Assuming 'category' and 'total_deliveries' are the columns in the result set
                String category = result.getString("category");
                int totalDeliveries = result.getInt("total_deliveries");
                resultMessage.append("Категорія: ").append(category)
                        .append(", Загальна кількість доставок: ").append(totalDeliveries)
                        .append("\n");
            }
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestDeliveries2Action(ActionEvent event) {
        String query = "SELECT category, AVG(price_one_product) AS average_price FROM deliveries GROUP BY category";
        connect = database.connectDb();
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                // Тепер 'category' та 'average_price' є стовпцями у наборі результатів
                String category = result.getString("category");
                double averagePrice = result.getDouble("average_price"); // Використовуємо getDouble для середньої ціни
                resultMessage.append("Категорія: ").append(category)
                        .append(", Середня ціна одного продукту: ").append(String.format("%.2f", averagePrice)) // Форматуємо середню ціну до двох знаків після коми
                        .append("\n");
            }
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestDeliveries3Action(ActionEvent event) {
        String query = "SELECT d.* FROM deliveries d JOIN (SELECT category, AVG(price_one_product) AS avg_price FROM deliveries GROUP BY category) avg_prices ON d.category = avg_prices.category WHERE d.price_one_product > avg_prices.avg_price";
        connect = database.connectDb();
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                // Тут потрібно вказати, які саме поля ви хочете вивести. Наприклад, якщо в таблиці deliveries є поля id, category, price_one_product, то:
                int id = result.getInt("productID");
                String category = result.getString("category");
                double priceOneProduct = result.getDouble("price_one_product");
                // Форматуємо вивід, використовуючи отримані дані
                resultMessage.append("productID: ").append(id)
                        .append(", Категорія: ").append(category)
                        .append(", Ціна одного продукту: ").append(String.format("%.2f", priceOneProduct))
                        .append("\n");
            }
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestDeliveries4Action(ActionEvent event) {
        String query = "SELECT d1.* FROM deliveries d1 JOIN (SELECT category, MAX(number) AS max_number FROM deliveries GROUP BY category) d2 ON d1.category = d2.category AND d1.number = d2.max_number";
        connect = database.connectDb();
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                // Тут потрібно вказати, які саме поля ви хочете вивести. Наприклад, якщо в таблиці deliveries є поля id, category, number, то:
                int id = result.getInt("productID");
                String category = result.getString("category");
                int number = result.getInt("number"); // Використовуємо getInt для кількості товару
                // Форматуємо вивід, використовуючи отримані дані
                resultMessage.append("productID: ").append(id)
                        .append(", Категорія: ").append(category)
                        .append(", Кількість товару: ").append(number)
                        .append("\n");
            }
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestDeliveries5Action(ActionEvent event) {
        String query = "SELECT g.name FROM goods g JOIN deliveries d ON g.productID = d.productID WHERE d.delivery_date > '2023-01-01'";
        connect = database.connectDb();
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                // Тут потрібно вказати, які саме поля ви хочете вивести. У цьому випадку, ми виводимо лише ім'я товару:
                String name = result.getString("name"); // Використовуємо getString для імені товару
                // Форматуємо вивід, використовуючи отримані дані
                resultMessage.append("Назва товару: ").append(name).append("\n");
            }
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestDeliveries6Action(ActionEvent event) {
        String query = "SELECT g.name, COUNT(d.deliveriesID) AS delivery_count FROM goods g JOIN deliveries d ON g.productID = d.productID GROUP BY g.name ORDER BY delivery_count DESC";
        connect = database.connectDb();
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                // Тут потрібно вказати, які саме поля ви хочете вивести. У цьому випадку, ми виводимо ім'я товару та кількість доставок:
                String name = result.getString("name"); // Використовуємо getString для імені товару
                int deliveryCount = result.getInt("delivery_count"); // Використовуємо getInt для кількості доставок
                // Форматуємо вивід, використовуючи отримані дані
                resultMessage.append("Назва товару: ").append(name)
                        .append(", Кількість доставок: ").append(deliveryCount)
                        .append("\n");
            }
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestDeliveries7Action(ActionEvent event) {
        String query = "SELECT g.name FROM goods g LEFT JOIN deliveries d ON g.productID = d.productID WHERE d.deliveriesID IS NULL";
        connect = database.connectDb();
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                // Тут потрібно вказати, які саме поля ви хочете вивести. У цьому випадку, ми виводимо лише ім'я товару:
                String name = result.getString("name"); // Використовуємо getString для імені товару
                // Форматуємо вивід, використовуючи отримані дані
                resultMessage.append("Назва товару: ").append(name).append("\n");
            }
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestSuppliers1Action(ActionEvent event) {
        String query = "SELECT g.name AS ProductName, s.name_of_company_or_supplier AS SupplierName, s.contact_information, s.location FROM goods g JOIN suppliers s ON g.company_name = s.name_of_company_or_supplier";
        connect = database.connectDb(); // Connect to the database
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query); // Prepare the SQL query
            result = prepare.executeQuery(); // Execute the query
            while (result.next()) {
                // Retrieve the values for each column
                String productName = result.getString("ProductName");
                String supplierName = result.getString("SupplierName");
                String contactInformation = result.getString("contact_information");
                String location = result.getString("location");
                // Format and append the retrieved data to the result message
                resultMessage.append("Назва товару: ").append(productName)
                        .append(", Назва постачальника: ").append(supplierName)
                        .append(", Контактна інформація: ").append(contactInformation)
                        .append(", Місцезнаходження: ").append(location).append("\n");
            }
            // Display the result message in an information alert
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            // Display an error alert if an exception occurs
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestSuppliers2Action(ActionEvent event) {
        String query = "SELECT category, SUM(quantity_in_stock) AS TotalQuantityInStock FROM goods GROUP BY category";
        connect = database.connectDb(); // Connect to the database
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query); // Prepare the SQL query
            result = prepare.executeQuery(); // Execute the query
            while (result.next()) {
                // Retrieve the values for each column
                String category = result.getString("category");
                int totalQuantityInStock = result.getInt("TotalQuantityInStock");
                // Format and append the retrieved data to the result message
                resultMessage.append("Категорія: ").append(category)
                        .append(", Загальна кількість на складі: ").append(totalQuantityInStock).append("\n");
            }
            // Display the result message in an information alert
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            // Display an error alert if an exception occurs
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestSuppliers3Action(ActionEvent event) {
        String query = "SELECT DISTINCT s.name_of_company_or_supplier, s.contact_information FROM suppliers s JOIN goods g ON s.name_of_company_or_supplier = g.company_name WHERE g.price > 400";
        connect = database.connectDb(); // Connect to the database
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query); // Prepare the SQL query
            result = prepare.executeQuery(); // Execute the query
            while (result.next()) {
                // Retrieve the values for each column
                String supplierName = result.getString("name_of_company_or_supplier");
                String contactInformation = result.getString("contact_information");
                // Format and append the retrieved data to the result message
                resultMessage.append("Назва постачальника: ").append(supplierName)
                        .append(", Контактна інформація: ").append(contactInformation).append("\n");
            }
            // Display the result message in an information alert
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            // Display an error alert if an exception occurs
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestSuppliers4Action(ActionEvent event) {
        String query = "SELECT g.company_name, AVG(g.price) AS AveragePrice FROM goods g GROUP BY g.company_name";
        connect = database.connectDb(); // Connect to the database
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query); // Prepare the SQL query
            result = prepare.executeQuery(); // Execute the query
            while (result.next()) {
                // Retrieve the values for each column
                String companyName = result.getString("company_name");
                double averagePrice = result.getDouble("AveragePrice");
                // Format and append the retrieved data to the result message
                resultMessage.append("Назва компанії: ").append(companyName)
                        .append(", Середня ціна: ").append(averagePrice).append("\n");
            }
            // Display the result message in an information alert
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            // Display an error alert if an exception occurs
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestSuppliers5Action(ActionEvent event) {
        String query = "SELECT g.name AS ProductName, g.category, s.location FROM goods g JOIN suppliers s ON g.company_name = s.name_of_company_or_supplier WHERE g.quantity_in_stock > 0 AND g.category = 'Електроніка'";
        connect = database.connectDb(); // Connect to the database
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query); // Prepare the SQL query
            result = prepare.executeQuery(); // Execute the query
            while (result.next()) {
                // Retrieve the values for each column
                String productName = result.getString("ProductName");
                String category = result.getString("category"); // Assuming you want to display the category as well
                String location = result.getString("location");
                // Format and append the retrieved data to the result message
                resultMessage.append("Назва товару: ").append(productName)
                        .append(", Категорія: ").append(category) // Displaying the category
                        .append(", Місцезнаходження: ").append(location).append("\n");
            }
            // Display the result message in an information alert
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            // Display an error alert if an exception occurs
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestSuppliers6Action(ActionEvent event) {
        // Updated SQL query to match the provided one
        String query = "SELECT g.name AS НазваТовару, g.quantity_in_stock AS КількістьНаСкладі, s.name_of_company_or_supplier AS НазваПостачальника FROM goods g JOIN suppliers s ON g.company_name = s.name_of_company_or_supplier WHERE g.quantity_in_stock < ( SELECT AVG(quantity_in_stock) FROM goods )";
        connect = database.connectDb(); // Connect to the database
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query); // Prepare the SQL query
            result = prepare.executeQuery(); // Execute the query
            while (result.next()) {
                // Retrieve the values for each column
                String productName = result.getString("НазваТовару");
                int quantityInStock = result.getInt("КількістьНаСкладі");
                String supplierName = result.getString("НазваПостачальника");
                // Format and append the retrieved data to the result message
                resultMessage.append("Назва товару: ").append(productName)
                        .append(", Кількість на складі: ").append(quantityInStock)
                        .append(", Назва постачальника: ").append(supplierName).append("\n");
            }
            // Display the result message in an information alert
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            // Display an error alert if an exception occurs
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestSuppliers7Action(ActionEvent event) {
        // Updated SQL query to fetch the most expensive product within each category and its supplier's details
        String query = "SELECT g.category, g.name AS ProductName, g.price, s.name_of_company_or_supplier AS SupplierName " +
                "FROM goods g " +
                "JOIN suppliers s ON g.company_name = s.name_of_company_or_supplier " +
                "WHERE (g.category, g.price) IN ( " +
                "  SELECT category, MAX(price) " +
                "  FROM goods " +
                "  GROUP BY category " +
                ")";
        connect = database.connectDb(); // Connect to the database
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query); // Prepare the SQL query
            result = prepare.executeQuery(); // Execute the query
            while (result.next()) {
                // Retrieve the values for each column
                String category = result.getString("category");
                String productName = result.getString("ProductName");
                Double price = result.getDouble("price");
                String supplierName = result.getString("SupplierName");
                // Format and append the retrieved data to the result message
                resultMessage.append("Категорія: ").append(category)
                        .append(", Назва товару: ").append(productName)
                        .append(", Ціна: ").append(price)
                        .append(", Назва постачальника: ").append(supplierName).append("\n");
            }
            // Display the result message in an information alert
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            // Display an error alert if an exception occurs
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestInventory1Action(ActionEvent event) {
        String query = "SELECT g.name AS ProductName, g.category " +
                "FROM goods g " +
                "LEFT JOIN inventory i ON g.productID = i.product " +
                "WHERE i.product IS NULL";

        connect = database.connectDb(); // Connect to the database
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query); // Prepare the SQL query
            result = prepare.executeQuery(); // Execute the query
            while (result.next()) {
                // Retrieve the values for each column
                String productName = result.getString("ProductName");
                String category = result.getString("category");
                // Format and append the retrieved data to the result message
                resultMessage.append("Назва товару: ").append(productName)
                        .append(", Категорія: ").append(category).append("\n");
            }
            // Display the result message in an information alert
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            // Display an error alert if an exception occurs
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestInventory2Action(ActionEvent event) {
        String query = "SELECT g.category, SUM(g.quantity_in_stock) AS TotalQuantityInStock " +
                "FROM goods g " +
                "GROUP BY g.category";

        connect = database.connectDb(); // Connect to the database
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query); // Prepare the SQL query
            result = prepare.executeQuery(); // Execute the query
            while (result.next()) {
                // Retrieve the values for each column
                String category = result.getString("category");
                int totalQuantityInStock = result.getInt("TotalQuantityInStock");
                // Format and append the retrieved data to the result message
                resultMessage.append("Категорія: ").append(category)
                        .append(", Загальна кількість на складі: ").append(totalQuantityInStock).append("\n");
            }
            // Display the result message in an information alert
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            // Display an error alert if an exception occurs
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestInventory3Action(ActionEvent event) {
        String query = "SELECT g.category, AVG(g.price) AS AveragePrice " +
                "FROM goods g " +
                "WHERE g.quantity_in_stock > 0 " +
                "GROUP BY g.category";

        connect = database.connectDb(); // Connect to the database
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query); // Prepare the SQL query
            result = prepare.executeQuery(); // Execute the query
            while (result.next()) {
                // Retrieve the values for each column
                String category = result.getString("category");
                Double averagePrice = result.getDouble("AveragePrice");
                // Format and append the retrieved data to the result message
                resultMessage.append("Категорія: ").append(category)
                        .append(", Середня ціна: ").append(averagePrice).append("\n");
            }
            // Display the result message in an information alert
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            // Display an error alert if an exception occurs
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestInventory4Action(ActionEvent event) {
        String query = "SELECT name AS Назва_Товару, price AS Ціна FROM goods";

        connect = database.connectDb(); // Connect to the database
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query); // Prepare the SQL query
            result = prepare.executeQuery(); // Execute the query
            while (result.next()) {
                // Retrieve the values for each column
                String productName = result.getString("Назва_Товару");
                Double price = result.getDouble("Ціна");
                // Format and append the retrieved data to the result message
                resultMessage.append("Назва товару: ").append(productName)
                        .append(", Ціна: ").append(price).append("\n");
            }
            // Display the result message in an information alert
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            // Display an error alert if an exception occurs
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }
    public void handleRequestInventory5Action(ActionEvent event) {
        String query = "SELECT company_name AS Назва_Компанії, COUNT(*) AS Кількість_Товарів " +
                "FROM goods " +
                "GROUP BY company_name " +
                "ORDER BY Кількість_Товарів DESC";

        connect = database.connectDb(); // Connect to the database
        StringBuilder resultMessage = new StringBuilder();
        try {
            prepare = connect.prepareStatement(query); // Prepare the SQL query
            result = prepare.executeQuery(); // Execute the query
            while (result.next()) {
                // Retrieve the values for each column
                String companyName = result.getString("Назва_Компанії");
                int productCount = result.getInt("Кількість_Товарів");
                // Format and append the retrieved data to the result message
                resultMessage.append("Назва компанії: ").append(companyName)
                        .append(", Кількість товарів: ").append(productCount).append("\n");
            }
            // Display the result message in an information alert
            showAlert(Alert.AlertType.INFORMATION, "Результати запиту", resultMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            // Display an error alert if an exception occurs
            showAlert(Alert.AlertType.ERROR, "Помилка", "Помилка при виконанні запиту.");
        }
    }


    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Зробити вікно сповіщення змінним за розміром
        alert.setResizable(true);
        // Встановити мінімальний розмір вікна
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(400); // Ви можете змінити це значення відповідно до ваших потреб

        alert.showAndWait();
    }
    public ObservableList<goodsData> goodsListData(){
        ObservableList<goodsData> godList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM goods";

        connect = database.connectDb();

        try{
            goodsData god;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()){
                god = new goodsData(
                        result.getString("productID"),
                        result.getString("name"),
                        result.getString("company_name"),
                        result.getString("customer_address"),
                        result.getString("category"),
                        result.getString("price"),
                        result.getString("quantity_in_stock"),
                        result.getString("data"));


                godList.add(god);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return godList;
    }

    private ObservableList<goodsData> goodsList;

    public void goodsShowData(){
        goodsList = goodsListData();

        goods_col_productID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        goods_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        goods_col_companyName.setCellValueFactory(new PropertyValueFactory<>("company_name"));
        goods_col_customerAddress.setCellValueFactory(new PropertyValueFactory<>("customer_address"));
        goods_col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        goods_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        goods_col_quantityInStock.setCellValueFactory(new PropertyValueFactory<>("quantity_in_stock"));
        goods_col_data.setCellValueFactory(new PropertyValueFactory<>("data"));

        goods_tableView.setItems(goodsList);

    }
    public ObservableList<deliveriesData> deliveriesListData(){
        ObservableList<deliveriesData> delList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM deliveries";

        connect = database.connectDb();

        try{
            deliveriesData del;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()){
                del = new deliveriesData(
                        result.getString("deliveriesID"),
                        result.getString("productID"),
                        result.getString("number"),
                        result.getString("category"),
                        result.getString("delivery_date"),
                        result.getString("price_one_product"));


                delList.add(del);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return delList;
    }

    private ObservableList<deliveriesData> delList;

    public void deliveriesShowData(){
        delList = deliveriesListData();

        deliveries_col_deliveriesID.setCellValueFactory(new PropertyValueFactory<>("deliveriesID"));
        deliveries_col_productID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        deliveries_col_number.setCellValueFactory(new PropertyValueFactory<>("number"));
        deliveries_col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        deliveries_col_deliveryDate.setCellValueFactory(new PropertyValueFactory<>("delivery_date"));
        deliveries_col_priceOneProduct.setCellValueFactory(new PropertyValueFactory<>("price_one_product"));

        deliveries_tableView.setItems(delList);

    }
    public ObservableList<suppliersData> suppliersListData(){
        ObservableList<suppliersData> supList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM suppliers";

        connect = database.connectDb();

        try{
            suppliersData sup;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()){
                sup = new suppliersData(
                        result.getString("vendorID"),
                        result.getString("name_of_company_or_supplier"),
                        result.getString("contact_information"),
                        result.getString("location"));

                supList.add(sup);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supList;
    }

    private ObservableList<suppliersData> supList;

    public void suppliersShowData(){
        supList = suppliersListData();

        suppliers_col_vendorID.setCellValueFactory(new PropertyValueFactory<>("vendorID"));
        suppliers_col_nameOfcompanyOrsupplier.setCellValueFactory(new PropertyValueFactory<>("name_of_company_or_supplier"));
        suppliers_col_contactInformation.setCellValueFactory(new PropertyValueFactory<>("contact_information"));
        suppliers_col_location.setCellValueFactory(new PropertyValueFactory<>("location"));

        suppliers_tableView.setItems(supList);

    }

    public ObservableList<inventoryData> inventoryListData(){
        ObservableList<inventoryData> inveList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM inventory";

        connect = database.connectDb();

        try{
            inventoryData inve;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()){
                inve = new inventoryData(
                        result.getString("inventoryID"),
                        result.getString("data"),
                        result.getString("product"));

                inveList.add(inve);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inveList;
    }

    private ObservableList<inventoryData> inveList;

    public void inventoryShowData(){
        inveList = inventoryListData();

        inventory_col_inventoryID.setCellValueFactory(new PropertyValueFactory<>("inventoryID"));
        inventory_col_data.setCellValueFactory(new PropertyValueFactory<>("data"));
        inventory_col_product.setCellValueFactory(new PropertyValueFactory<>("product"));

        inventory_tableView.setItems(inveList);

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
        // Спочатку встановлюємо стандартний стиль для всіх кнопок
//        String defaultStyle = "-fx-background-color: #f0f0f0;"; // Білий колір фону
//        String activeStyle = "-fx-background-color: #ffae00;"; // Синій колір фону для активної кнопки
//
//        dashboard_btn.setStyle(defaultStyle);
//        addProducts_btn.setStyle(defaultStyle);
//        employees_bts.setStyle(defaultStyle);
//        goods_btn.setStyle(defaultStyle);
//        deliveries_btn.setStyle(defaultStyle);
//        suppliers_btn.setStyle(defaultStyle);
//        inventory_btn.setStyle(defaultStyle);

        // Приховуємо всі форми спочатку
        dashboard_form.setVisible(false);
        addProducts_form.setVisible(false);
        employees_form.setVisible(false);
        goods_form.setVisible(false);
        deliveries_form.setVisible(false);
        suppliers_form.setVisible(false);
        inventory_form.setVisible(false);

        // Визначаємо, яка кнопка була натиснута, показуємо відповідну форму і змінюємо стиль кнопки
        if(event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
//            dashboard_btn.setStyle(activeStyle);
        } else if (event.getSource() == addProducts_btn) {
            addProducts_form.setVisible(true);
//            addProducts_btn.setStyle(activeStyle);
        } else if (event.getSource() == employees_bts) {
            employees_form.setVisible(true);
//            employees_bts.setStyle(activeStyle);
        } else if (event.getSource() == goods_btn) {
            goods_form.setVisible(true);
//            goods_btn.setStyle(activeStyle);
        } else if (event.getSource() == deliveries_btn) {
            deliveries_form.setVisible(true);
//            deliveries_btn.setStyle(activeStyle);
        } else if (event.getSource() == suppliers_btn) {
            suppliers_form.setVisible(true);
//            suppliers_btn.setStyle(activeStyle);
        } else if (event.getSource() == inventory_btn) {
            inventory_form.setVisible(true);
//            inventory_btn.setStyle(activeStyle);
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

            goodsShowData();
            deliveriesShowData();
            suppliersShowData();
            inventoryShowData();

    }
}
