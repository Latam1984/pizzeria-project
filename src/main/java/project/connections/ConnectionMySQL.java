package project.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Aleksey on 26.04.2017.
 */
public class ConnectionMySQL implements ConnectionDB {
    private static final String dataBaseURL = "jdbc:mysql://localhost:3306/pizzeria?useSSL=true";
    private static final String mySqlDriver = "com.mysql.jdbc.Driver";

    private static final String login = "root";
    private static final String password = "17198408";

    private static ConnectionMySQL connectionMySQL;

    public ConnectionMySQL() {
    }



    public static ConnectionMySQL getConnectionMySQL() {
        if (connectionMySQL == null) {
            connectionMySQL = new ConnectionMySQL();
            System.out.println("Instance of connectionMySQL created.");
        }
        return connectionMySQL;
    }


    public  Connection getConnection() throws SQLException {
        System.out.println("Connection to DB");
        return DriverManager.getConnection(dataBaseURL, login, password);
    }
}
