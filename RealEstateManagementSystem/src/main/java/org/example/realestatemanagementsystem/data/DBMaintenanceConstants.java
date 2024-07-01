package org.example.realestatemanagementsystem.data;

public interface DBMaintenanceConstants {
    String SELECT_ALL_MAINTENANCE_REQUESTS = "SELECT * FROM maintenance_requests";
    String INSERT_MAINTENANCE = "INSERT INTO maintenance_requests(property_id, status, description, priority,maintenance_costs) VALUES(?, ?, ?, ?, ?)";
    String UPDATE_MAINTENANCE = "UPDATE maintenance_requests SET status = ?, priority = ?, description = ?, maintenance_costs = ? WHERE id = ?";
    String DELETE_MAINTENANCE = "DELETE FROM maintenance_requests WHERE id = ?";
    String COUNT_OPEN_MAINTENANCE_BY_STATUS = "SELECT status, COUNT(*) AS count FROM maintenance_requests WHERE status != 'Completed' GROUP BY status";

    }
