package org.example.realestatemanagementsystem.data;

import java.sql.Date;

public class LeaseContractsData {
    private int id;
    private int tenantId;
    private String tenantName;
    private int propertyId;
    private String propertyAddress;
    private Date startDate;
    private Date endDate;
    private Double rentAmount;
    private String paymentStatus;
    private String contract;


    public LeaseContractsData(int id, int tenantId,String tenantName, int propertyId, String propertyAddress, Date startDate, Date endDate, Double rentAmount, String paymentStatus, String contract) {
        this.id = id;
        this.tenantId = tenantId;
        this.tenantName = tenantName;
        this.propertyId = propertyId;
        this.propertyAddress =propertyAddress;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentAmount = rentAmount;
        this.paymentStatus = paymentStatus;
        this.contract = contract;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(Double rentAmount) {
        this.rentAmount = rentAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }
}
