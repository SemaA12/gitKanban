package org.example.realestatemanagementsystem.data;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PaymentData {
    private int id;
    private int tenantId;
    private String tenantName;
    private int leaseContractId;
    private String propertyAddress;
    private Double amount;
    private LocalDate paymentDate;


    public PaymentData(int id, int tenantId, String tenantName, int leaseContractId,String propertyAddress, Double amount, LocalDate paymentDate) {
        this.id = id;
        this.tenantId = tenantId;
        this.tenantName = tenantName;
        this.leaseContractId = leaseContractId;
        this.propertyAddress = propertyAddress;
        this.amount = amount;
        this.paymentDate = paymentDate;
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

    public int getLeaseContractId() {
        return leaseContractId;
    }

    public void setLeaseContractId(int leaseContractId) {
        this.leaseContractId = leaseContractId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
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