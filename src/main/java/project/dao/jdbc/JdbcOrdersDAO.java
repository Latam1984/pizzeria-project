package project.dao.jdbc;

import project.connections.ConnectionDB;
import project.dao.OrdersDAO;
import project.entities.Orders;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcOrdersDAO implements OrdersDAO<Orders, Integer> {

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
    private ConnectionDB connectionDB;

    public JdbcOrdersDAO(ConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }


    @Override
    public Integer save(Orders order) {
        Integer id;
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
             java.sql.Statement statement = connection.createStatement()) {
            preparedStatement.setBigDecimal(1, order.getOrder_price());
            preparedStatement.executeUpdate();
            ResultSet resultSet = statement.executeQuery(GET_LAST_INSERTED);
            resultSet.next();
            id = resultSet.getInt(1);
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Orders findByID(Integer id) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Orders foundedOrder = new Orders(0, timestamp, new BigDecimal(0));
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                foundedOrder = new Orders(
                        resultSet.getInt("ID"),
                        resultSet.getTimestamp("DATE"),
                        resultSet.getBigDecimal("ORDER_PRICE")
                );
            }
            return foundedOrder;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer update(Orders order) {
        Integer id;
        try(Connection connection = connectionDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)
        ) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setTimestamp(1, order.getDate());
            preparedStatement.setBigDecimal(1, order.getOrder_price());
            preparedStatement.executeUpdate();
            id = order.getId();
        return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer delete(Orders order) {
        Integer id;
        try (Connection connection = connectionDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
            id = resultSet.getInt("ID");
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Orders> findAll() {
        List<Orders> allOrders = new ArrayList<>();
        try (Connection connection = connectionDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
        ) {ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            allOrders.add(new Orders(
                    resultSet.getInt("ID"),
                    resultSet.getTimestamp("DATE"),
                    resultSet.getBigDecimal("ORDER_PRICE")
            ));
        }return allOrders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
