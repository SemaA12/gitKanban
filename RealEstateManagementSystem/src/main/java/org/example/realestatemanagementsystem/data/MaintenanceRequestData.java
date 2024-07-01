package org.example.realestatemanagementsystem.data;

public class MaintenanceRequestData {
    private int id;
    private String status;
    private int propertyId;
    private String description;
    private String priority;
    private String propertyAddress;
    private double maintenanceCosts;

    public MaintenanceRequestData(int id, String status, int propertyId, String description, String priority, String propertyAddress, double maintenanceCosts) {
        this.id = id;
        this.status = status;
        this.propertyId = propertyId;
        this.description = description;
        this.priority = priority;
        this.propertyAddress = propertyAddress;
        this.maintenanceCosts = maintenanceCosts;
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

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public double getMaintenanceCosts() {
        return maintenanceCosts;
    }

    public void setMaintenanceCosts(double maintenanceCosts) {
        this.maintenanceCosts = maintenanceCosts;
    }
}
