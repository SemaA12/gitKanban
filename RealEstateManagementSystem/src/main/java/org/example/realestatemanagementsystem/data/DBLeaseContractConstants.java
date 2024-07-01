package org.example.realestatemanagementsystem.data;

public interface DBLeaseContractConstants {
    String SELECT_LEASE_CONTRACTS = "SELECT * FROM lease_contracts WHERE id = ?";
    String INSERT_LEASE_CONTRACT = "INSERT INTO lease_contracts (tenant_id, property_id, start_date, end_date, rent_amount, payment_status, contract) VALUES(?,?,?,?,?,?,?)";
    String SELECT_CONTRACT = "SELECT contract FROM lease_contracts WHERE id = ?";
    String SELECT_ALL_LEASE_CONTRACTS =  "SELECT * FROM lease_contracts";
    String SELECT_LEASE_CONTRACT_ID_BY_TENANT =  "SELECT id FROM lease_contracts WHERE tenant_id = ?";
    String SELECT_LEASE_CONTRACT_ID = "SELECT id FROM lease_contracts";

}
