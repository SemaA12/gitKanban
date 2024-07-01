package org.example.realestatemanagementsystem.data;

public interface DBPaymentConstants {

    String SELECT_PAYMENTS = "SELECT * FROM payments WHERE id = ?";
    String INSERT_PAYMENT = "INSERT INTO payments (tenant_id, lease_contract_id, amount, payment_date) VALUES(?,?,?,?)";
    String SELECT_ALL_PAYMENTS = "SELECT * FROM payments";
    String SUM_TOTAL_PAYMENTS =
            "SELECT SUM(amount) AS total FROM payments";

}
