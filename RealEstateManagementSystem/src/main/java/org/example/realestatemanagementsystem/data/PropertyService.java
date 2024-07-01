package org.example.realestatemanagementsystem.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PropertyService {
    Connection connect;

    public ObservableList<String> getPropertiesWithoutLeaseContract() {
        ObservableList<String> propertiesList = FXCollections.observableArrayList();
        Connection connect;
        try {
            String query = DBPropertyConstants.SELECT_PROPERTIES_WITHOUT_LEASE_CONTRACTS;
            connect = DBConnection.dbConnection();
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                propertiesList.add(resultSet.getString("address"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return propertiesList;
    }

    private List<String> getPropertiesInPeriod(LocalDate moveInDate, LocalDate moveOutDate) {
        List<String> properties = new ArrayList<>();
        try {
            String query = DBPropertyConstants.SELECT_PROPERTIES_BY_LEASE_DATE;
            try (Connection connect = DBConnection.dbConnection();
                 PreparedStatement prepare = connect.prepareStatement(query)) {
                prepare.setDate(1, Date.valueOf(moveOutDate));
                prepare.setDate(2, Date.valueOf(moveInDate));
                try (ResultSet resultSet = prepare.executeQuery()) {
                    while (resultSet.next()) {
                        properties.add(resultSet.getString("address"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static ObservableList<String> getAvailableProperties(LocalDate startDate, LocalDate endDate) {
        ObservableList<String> availablePropertiesList = FXCollections.observableArrayList();
        if (startDate == null || endDate == null) {
            return availablePropertiesList;
        }
        String sql = DBPropertyConstants.SELECT_AVAILABLE_PROPERTIES;

        try (Connection connect = DBConnection.dbConnection();
             PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setDate(1, Date.valueOf(endDate));
            prepare.setDate(2, Date.valueOf(startDate));
            ResultSet resultSet = prepare.executeQuery();

            while (resultSet.next()) {
                availablePropertiesList.add(resultSet.getString("address"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return availablePropertiesList;
    }

    public static List<PropertyData> getNotUnderMaintenanceProperties(List<String> excludedStatuses) {
        List<PropertyData> notUnderMaintenancePropertiesList = new ArrayList<>();

        StringBuilder sqlBuilder = new StringBuilder("SELECT id, address FROM properties WHERE status NOT IN (");
        for (int i = 0; i < excludedStatuses.size(); i++) {
            sqlBuilder.append("?");
            if (i < excludedStatuses.size() - 1) {
                sqlBuilder.append(",");
            }
        }
        sqlBuilder.append(")");

        String sql = sqlBuilder.toString();

        try (Connection connect = DBConnection.dbConnection();
             PreparedStatement prepare = connect.prepareStatement(sql)) {

            for (int i = 0; i < excludedStatuses.size(); i++) {
                prepare.setString(i + 1, excludedStatuses.get(i));
            }

            ResultSet resultSet = prepare.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String address = resultSet.getString("address");
                PropertyData property = new PropertyData(id, address);
                notUnderMaintenancePropertiesList.add(property);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notUnderMaintenancePropertiesList;
    }


    public static ObservableList<String> getAllProperties() {
        ObservableList<String> allPropertiesList = FXCollections.observableArrayList();

        String sql = DBPropertyConstants.SELECT_ALL_PROPERTIES;

        try (Connection connect = DBConnection.dbConnection();
             PreparedStatement prepare = connect.prepareStatement(sql)) {

            ResultSet resultSet = prepare.executeQuery();

            while (resultSet.next()) {
                String address = resultSet.getString("address");
                allPropertiesList.add(address);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allPropertiesList;
    }
}