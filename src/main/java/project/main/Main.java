package project.main;

import project.connections.ConnectionMySQL;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Aleksey on 26.04.2017.
 */
public class Main {
    public static void main(String[] args) {
        ConnectionMySQL connectionMySQL = ConnectionMySQL.getConnectionMySQL();
        Connection connection = null;
        Statement statement = null;


        try {
            connection = connectionMySQL.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql;
        sql = "SELECT * FROM USERS";

        try {
         connection.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
