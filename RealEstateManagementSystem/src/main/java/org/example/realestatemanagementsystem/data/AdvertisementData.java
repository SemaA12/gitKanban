package org.example.realestatemanagementsystem.data;

import java.sql.Date;

public class AdvertisementData {

    private int id;
    private int propertyId;
    private String propertyAddress;
    private String title;
    private String description;
    private Date publicationDate;
    private Date expirationDate;
    private double advertisementCosts;


    public AdvertisementData(int id, int propertyId, String propertyAddress, String title, String description, Date publicationDate, Date expirationDate, double advertisementCosts) {
        this.id = id;
        this.propertyId = propertyId;
        this.propertyAddress = propertyAddress;
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
        this.expirationDate = expirationDate;
        this.advertisementCosts = advertisementCosts;
    }

    public AdvertisementData(int id, int propertyId, String title, String description, Date publicationDate, Date expirationDate, double advertisementCosts) {
        this.id = id;
        this.propertyId = propertyId;
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
        this.expirationDate = expirationDate;
        this.advertisementCosts = advertisementCosts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getAdvertisementCosts() {
        return advertisementCosts;
    }

    public void setAdvertisementCosts(double advertisementCosts) {
        this.advertisementCosts = advertisementCosts;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }


}
