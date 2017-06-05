package project.dao.jdbc;

import org.slf4j.Logger;
import project.connections.ConnectionDB;
import project.dao.OrdersDAO;
import project.entities.Orders;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcOrdersDAO implements OrdersDAO<Orders, Integer> {

    /**
     * Logger slf4j. All logs saves in target.jdbc
     */
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JdbcOrdersDAO.class);

    /**
     * A pattern of an SQL command (without particular values)
     * for saving an order in a database
     */
    private static final String SAVE = "INSERT INTO ORDERS (DATE, ORDER_PRICE) VALUES (?, ?)";

    /**
     * A pattern of an SQL command (without particular value)
     * for finding an order in a database by id
     */
    private static final String FIND_BY_ID = "SELECT * FROM ORDERS WHERE ID = ?";

    /**
     * A pattern of an SQL command (without particular value)
     * for update an order in a database by id
     */
    private static final String UPDATE = "UPDATE ORDERS SET ORDER_PRICE = ? WHERE ID = ? ";

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
     * inserted order in a database
     */
    private static final String GET_LAST_INSERTED = "SELECT LAST_INSERT_ID()";

    /**
     * Connection to database
     */
    private ConnectionDB connectionDB;

    /**
     * @param connectionDB a connection to a database
     */
    public JdbcOrdersDAO(ConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

    /**
     * Method saves a new component in database
     *
     * @param order saving in  database
     * @return component id, if the component was saving to database successfully
     */
    @Override
    public Integer save(Orders order) {
        Integer id;

        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
             java.sql.Statement statement = connection.createStatement()) {
            LOGGER.info("Save order to DB");
            preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setBigDecimal(2, order.getOrder_price());
            preparedStatement.executeUpdate();
            ResultSet resultSet = statement.executeQuery(GET_LAST_INSERTED);
            resultSet.next();
            id = resultSet.getInt(1);
            return id;
        } catch (SQLException e) {
            LOGGER.error("Cannot add order to DB");
            throw new RuntimeException(e);
        }
    }

    /**
     * Method which find current order by id
     *
     * @param id the id of an order
     * @return an order with entered id
     * or new order with empty parameters if component with this id does not exist
     */
    @Override
    public Orders findByID(Integer id) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Orders foundedOrder = new Orders(0, timestamp, new BigDecimal(0));
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            LOGGER.info("Looking for an order by id " + id);
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
            LOGGER.error("Cannot find order by id " + id);
            throw new RuntimeException(e);
        }
    }

    /**
     * Method updates components in the database
     *
     * @param order with update fields
     */
    @Override
    public void update(Orders order) {
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)
        ) {
            preparedStatement.setBigDecimal(1, order.getOrder_price());
            preparedStatement.setInt(2, order.getId());
            preparedStatement.executeUpdate();
            LOGGER.info("Order updated ");
        } catch (SQLException e) {
            LOGGER.error("Cannot update order ");
            throw new RuntimeException(e);
        }
    }

    /**
     * Method removes an order from database
     *
     * @param order which must be removed
     */
    @Override
    public void delete(Orders order) {
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.executeUpdate();
            LOGGER.info("Order deleted ");
        } catch (SQLException e) {
            LOGGER.error("Order cannot be delete ");
            throw new RuntimeException(e);
        }
    }

    /**
     * Method returns all orders from the database
     *
     * @return list of all orders from the database
     */
    @Override
    public List<Orders> findAll() {
        List<Orders> allOrders = new ArrayList<>();
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                allOrders.add(new Orders(
                        resultSet.getInt("ID"),
                        resultSet.getTimestamp("DATE"),
                        resultSet.getBigDecimal("ORDER_PRICE")
                ));
                LOGGER.info("Loaded all orders from DB ");
            }
            return allOrders;
        } catch (SQLException e) {
            LOGGER.error("Cannot losd all orders from DB ");
            throw new RuntimeException(e);
        }
    }
}
