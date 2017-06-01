package project.dao.jdbc;

import project.dao.OrdersDAO;
import project.entities.Orders;

import java.io.Serializable;
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


    @Override
    public Integer save(Orders orders) {
        return null;
    }

    @Override
    public Orders findByID(Integer integer) {
        return null;
    }

    @Override
    public Integer update(Integer integer) {
        return null;
    }

    @Override
    public Integer delete(Integer integer) {
        return null;
    }

    @Override
    public List<Orders> findAll() {
        return null;
    }
}
