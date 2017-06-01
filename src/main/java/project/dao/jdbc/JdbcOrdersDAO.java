package project.dao.jdbc;

import project.connections.ConnectionDB;
import project.connections.ConnectionMySQL;
import project.dao.OrdersDAO;
import project.entities.Orders;

import java.beans.Statement;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcOrdersDAO implements OrdersDAO <Orders, Integer> {

    /**
     * A pattern of an SQL command (without particular values)
     * for saving an order in a database
     */
    private static final String SAVE = "INSERT INTO ORDERS (ORDER_PRICE, USER_ID) VALUES (?, ?)";

    /**
     * A pattern of an SQL command (without particular value)
     * for finding an order in a database by id
     */
    private static final String FIND_BY_ID = "SELECT * FROM ORDERS WHERE ID = ?";

    /**
     * A pattern of an SQL command (without particular value)
     * for update an order in a database by id
     */
    private static final String UPDATE = "UPDATE ORDERS SET ORDER_PRICE = ? AND USER_ID = ? WHERE ID = ? ";

    /**
     * A pattern of an SQL command (without particular value)
     * for delete an order in a database by id
     */
    private static final String DELETE = "DELETE FROM ORDERS WHERE ID = ?";

    /**
     * A pattern of an SQL command (without particular value)
     * for finding all orders in a database by id
     */
    private static final String FIND_ALL = "SELECT * FROM ORDERS";

    /**
     * A pattern of an SQL command  for finding a id from the last
     * inserted component in a database
     */
    private static final String GET_LAST_INSERTED = "SELECT LAST_INSERTED_ID()";

    /**
     * Connection to database
     */

    //private ConnectionMySQL connectionMySQL;

//    public JdbcOrdersDAO (ConnectionMySQL connectionMySQL){
//        this.connectionMySQL = connectionMySQL;
//    }
    private ConnectionDB connectionDB;

    public JdbcOrdersDAO (ConnectionDB connectionDB){
        this.connectionDB = connectionDB;
    }



    @Override
    public Integer save(Orders order) {
        Integer id;
        try(Connection connection = connectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
            java.sql.Statement statement = connection.createStatement()) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = statement.execute(GET_LAST_INSERTED);
            resultSet.next();
            id = resultSet.getInt(1);
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
            e.printStackTrace();
        }

    }

    @Override
    public Orders findByID(Integer integer) {
        return null;
    }

    @Override
    public Integer update(Orders order) {
        return null;
    }

    @Override
    public Integer delete(Orders order) {
        return null;
    }

    @Override
    public List<Orders> findAll() {
        return null;
    }
}
