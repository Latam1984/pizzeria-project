package project.connections;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Aleksey on 26.04.2017.
 */
public interface ConnectionDB {
    Connection getConnection() throws SQLException;
}
