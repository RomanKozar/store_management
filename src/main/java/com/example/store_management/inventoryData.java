package com.example.store_management;

public class inventoryData {

    private String inventoryID;
    private String data;
    private String product;

    public inventoryData(String inventoryID,String data, String product){
        this.inventoryID = inventoryID;
        this.data = data;
        this.product = product;
    }

    public String getInventoryID(){ return inventoryID;}
    public String getData(){ return data;}
    public String getProduct(){ return product;}
}
