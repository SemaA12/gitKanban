package org.example.realestatemanagementsystem.data;

public interface DBAdvertisementConstants {
    String SELECT_ALL_ADVERTISEMENTS = "SELECT * FROM advertisements";
    String INSERT_ADVERTISEMENT = "INSERT INTO advertisements(title, property_id, description, publication_date,expiration_date, advertisement_costs) VALUES(?, ?, ?, ?, ?, ?)";
    String DELETE_ADVERTISEMENT = "DELETE FROM advertisements WHERE id = ?";
    String UPDATE_ADVERTISEMENT = "UPDATE advertisements SET title=?, description=?, publication_date=?, expiration_date=?, advertisement_costs=? WHERE id=?";
}
