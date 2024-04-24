package com.example.store_management;

public class goodsData {

    private String productID;
    private String name;
    private String company_name;
    private String customer_address;
    private String category;
    private String price;
    private String quantity_in_stock;
    private String data;

    public goodsData(String productID,String name,String company_name,String customer_address,String category,String price,String quantity_in_stock,String data){
        this.productID = productID;
        this.name = name;
        this.company_name = company_name;
        this.customer_address = customer_address;
        this.category = category;
        this.price = price;
        this.quantity_in_stock = quantity_in_stock;
        this.data = data;
    }

    public String getProductID(){ return productID;}
    public String getName(){ return name;}
    public String getCompany_name(){ return company_name;}
    public String getCustomer_address(){ return customer_address;}
    public String getCategory(){ return category;}
    public String getPrice(){ return price;}
    public String getQuantity_in_stock(){ return quantity_in_stock;}
    public String getData(){ return data;}

}
