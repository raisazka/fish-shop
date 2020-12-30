package dao;

import database.ConnectionDatabase;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class GenericDAO {

    protected Connection connection;
    protected Statement stmt;

    public GenericDAO() {
        try {
            initConnetion();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to connect database");
        }
    }

    private void initConnetion() throws SQLException {
        connection = ConnectionDatabase.connect();
        stmt = connection.createStatement();
        if(connection == null)
            throw new SQLException("Failed Connect to Database");
    }

}
