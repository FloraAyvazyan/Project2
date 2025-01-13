package ge.tbc.testautomation.steps.dataBaseSteps;


import ge.tbc.testautomation.util.dataBase.MSSQLConnection;
import io.qameta.allure.Step;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseSteps {

    @Step("Retrieve username from database for username: {0}")
    public String getUserNameFromDatabase(String username) {
        String query = "SELECT username FROM users WHERE username = ?";
        try (Connection connection = MSSQLConnection.connect();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            // პარამეტრად გადაეცემა ის username, რომელიც გსურთ
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("username");
                } else {
                    throw new SQLException("User not found in the database.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in getUserNameFromDatabase: " + e.getMessage());
            return null;
        }
    }

    @Step("Retrieve password from database for username: {0}")
    public String getPasswordFromDatabase(String username) {
        String query = "SELECT password FROM users WHERE username = ?";
        try (Connection connection = MSSQLConnection.connect();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            // პარამეტრად გადაეცემა ის username, რომლის პაროლიც გსურთ
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String password = rs.getString("password");
                    System.out.println("Password for user " + username + ": " + password);  // პაროლი ბეჭდდება
                    return password;
                } else {
                    throw new SQLException("User not found in the database.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in getPasswordFromDatabase: " + e.getMessage());
            return null;
        }
    }



}
