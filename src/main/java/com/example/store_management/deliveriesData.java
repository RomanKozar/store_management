package com.example.store_management;

public class deliveriesData {

    private String deliveriesID;
    private String productID;
    private String number;
    private String category;
    private String delivery_date;
    private String price_one_product;

    public deliveriesData(String deliveriesID,String productID,String number,String category,String delivery_date,String price_one_product){
        this.deliveriesID = deliveriesID;
        this.productID = productID;
        this.number = number;
        this.category = category;
        this.delivery_date = delivery_date;
        this.price_one_product = price_one_product;
    }

    public String getDeliveriesID(){ return deliveriesID;}
    public String getProductID(){ return productID;}
    public String getNumber(){ return number;}
    public String getCategory(){ return category;}
    public String getDelivery_date(){ return delivery_date;}
    public String getPrice_one_product(){ return price_one_product;}
}
