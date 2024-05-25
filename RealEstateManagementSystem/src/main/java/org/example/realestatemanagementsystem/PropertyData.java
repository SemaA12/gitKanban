package org.example.realestatemanagementsystem;

import java.time.LocalDateTime;

public class PropertyData {
    private int id;
    private String status;
    private LocalDateTime createDate;
    private String address;
    private double size;
    private String amenities;
    private String type;
    private double rent;
    private String image;

    public PropertyData(int id, String status, LocalDateTime createDate, String address, double size, String amenities, String type, double rent, String image) {
        this.id = id;
        this.status = status;
        this.createDate = createDate;
        this.address = address;
        this.size = size;
        this.amenities = amenities;
        this.type = type;
        this.rent = rent;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
