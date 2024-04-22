package com.example.store_management;

public class productData {

    private String ID;
    private String productID;
    private String customerName;
    private String productName;
    private String status;
    private String number;



    public productData(String ID, String productID, String customerName, String productName, String status, String number){
        this.productID = productID;
        this.customerName = customerName;
        this.productName = productName;
        this.status = status;
        this.number = number;
        this.ID = ID;
    }

    public String getProductID(){
        return productID;
    }
    public String getCustomerName(){
        return customerName;
    }
    public String getProductName(){
        return productName;
    }
    public String getStatus(){
        return status;
    }
    public String getNumber(){
        return number;
    }
    public String getID() {return ID;}

}
