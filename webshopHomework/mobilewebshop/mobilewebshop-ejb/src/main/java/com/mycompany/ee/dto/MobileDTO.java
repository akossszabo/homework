package com.mycompany.ee.dto;

public class MobileDTO {
    
    private String id;
    private String type;
    private String manufacturer;
    private int price;
    private int piece;

    public MobileDTO(String id,String type,String manufacturer, int price, int piece){
        this.id=id;
        this.type=type;
        this.manufacturer = manufacturer;
        this.price = price;
        this.piece = piece;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }
    
    
}
