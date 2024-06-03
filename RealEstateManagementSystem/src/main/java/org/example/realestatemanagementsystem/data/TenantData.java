package org.example.realestatemanagementsystem.data;

import java.sql.Date;
import java.time.LocalDateTime;

public class TenantData {

    private int id;
    private String firstName;
    private String lastName;
    private LocalDateTime createDate;
    private String email;
    private String phone;
    private String address;
    private Date moveInDate;

    public TenantData(int id, String firstName, String lastName, LocalDateTime createDate, String email, String phone, String address, Date moveInDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createDate = createDate;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.moveInDate = moveInDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getMoveInDate() {
        return moveInDate;
    }

    public void setMoveInDate(Date moveInDate) {
        this.moveInDate = moveInDate;
    }
}
