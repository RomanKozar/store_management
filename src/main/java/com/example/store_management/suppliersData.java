package com.example.store_management;

public class suppliersData {

    private String vendorID;
    private String name_of_company_or_supplier;
    private String contact_information;
    private String location;

    public suppliersData(String vendorID,String name_of_company_or_supplier,String contact_information,String location){
        this.vendorID = vendorID;
        this.name_of_company_or_supplier = name_of_company_or_supplier;
        this.contact_information = contact_information;
        this.location = location;
    }

    public String getVendorID(){ return vendorID;}
    public String getName_of_company_or_supplier(){ return name_of_company_or_supplier;}
    public String getContact_information(){ return contact_information;}
    public String getLocation(){ return location;}
}
