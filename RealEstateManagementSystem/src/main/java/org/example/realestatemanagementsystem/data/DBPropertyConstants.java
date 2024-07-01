package org.example.realestatemanagementsystem.data;

public interface DBPropertyConstants {
    String SELECT_ADDRESS = "SELECT address FROM properties WHERE id = ?";
    String SELECT_TYPE = "SELECT type FROM properties WHERE id = ?";
    String SELECT_AMENITIES = "SELECT amenities FROM properties WHERE id = ?";
    String SELECT_ADDRESS_BY_LEASE_CONTRACTS = "SELECT address FROM properties WHERE id IN (SELECT property_id FROM lease_contracts WHERE property_id = ?)";
    String SELECT_ADDRESS_BY_PAYMENT_AND_CONTRACT = """
            SELECT address
            FROM properties
            WHERE id IN (SELECT property_id FROM lease_contracts WHERE id IN (SELECT lease_contract_id FROM payments WHERE lease_contract_id = ?))
            """;
    String SELECT_PROPERTY_ID = "SELECT id FROM properties WHERE address = ?";
    String SELECT_AVAILABLE_PROPERTIES = """
            SELECT address FROM properties p
            WHERE p.id NOT IN (
            SELECT lc.property_id FROM lease_contracts lc
            WHERE (lc.start_date < ? AND lc.end_date > ?))
            """;
    String SELECT_PROPERTIES_WITHOUT_LEASE_CONTRACTS = "SELECT address FROM properties WHERE id NOT IN (SELECT property_id FROM lease_contracts)";
    String SELECT_PROPERTIES_BY_LEASE_DATE = """
            SELECT address
            FROM properties
            WHERE id IN (SELECT property_id
            FROM lease_contracts
            WHERE start_date <= ? AND end_date >= ?)
            """;
    String UPDATE_PROPERTY_DETAILS = "UPDATE properties SET status=?, address=?, size=?, amenities=?, type=?, rent=?, image=? WHERE id=?";
    String DELETE_PROPERTY_BY_ID = "DELETE FROM properties WHERE id = ?";
    String SELECT_PROPERTY_BY_ADDRESS = "SELECT * FROM properties WHERE address = ?";
    String INSERT_PROPERTY = "INSERT INTO properties (status, address, size, amenities, type, rent, image) VALUES(?,?,?,?,?,?,?)";
    String SELECT_ALL_PROPERTIES = "SELECT * FROM properties";
    String SELECT_ID_AND_ADDRESS = "SELECT id, address FROM properties";
    String PROPERTY_COST_SUMMARIES = """
                SELECT p.address AS property_address,
                COALESCE(SUM(rent_amount),0) AS total_rent,
                COALESCE(sum(maintenance_costs),0) AS total_maintenance,
                COALESCE(sum(advertisement_costs),0) AS total_advertisements
                FROM properties p
                LEFT JOIN lease_contracts l ON p.id = l.property_id
                LEFT JOIN maintenance_requests mr ON l.property_id = mr.property_id
                LEFT JOIN advertisements a ON a.property_id = mr.property_id
                GROUP BY p.id
                """;
    String COUNT_AVAILABLE_PROPERTIES =
            "SELECT COUNT(*) AS count FROM properties WHERE status = 'Available'";
    String COUNT_SOLD_PROPERTIES = "SELECT COUNT(*) AS count FROM properties WHERE status = 'Sold'";

}
