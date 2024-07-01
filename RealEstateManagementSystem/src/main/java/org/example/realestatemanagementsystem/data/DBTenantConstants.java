package org.example.realestatemanagementsystem.data;

public interface DBTenantConstants {
    String SELECT_TENANT_NAME = "SELECT first_name, last_name FROM tenants WHERE email = ?";
    String SELECT_TENANT_NAME_BY_LEASE_CONTRACT = "SELECT first_name, last_name FROM tenants WHERE id IN (SELECT tenant_id FROM lease_contracts WHERE tenant_id = ?)";
    String SELECT_TENANT_ID = "SELECT id FROM tenants WHERE email = ?";
    String SELECT_TENANTS_WITHOUT_LEASE_CONTRACTS = "SELECT email FROM tenants WHERE id NOT IN (SELECT tenant_id FROM lease_contracts)";
    String SELECT_TENANTS_WITH_LEASE_CONTRACTS = "SELECT email FROM tenants WHERE id IN (SELECT tenant_id FROM lease_contracts)";
    String SELECT_ALL_TENANTS = "SELECT * FROM tenants";
    String UPDATE_TENANT_DETAILS = "UPDATE tenants SET first_name=?, last_name=?, email=? WHERE id=?";
    String SELECT_TENANTS = "SELECT * FROM tenants WHERE email = ?";
    String INSERT_TENANT ="INSERT INTO tenants (first_name, last_name, email) VALUES(?,?,?)";

}
