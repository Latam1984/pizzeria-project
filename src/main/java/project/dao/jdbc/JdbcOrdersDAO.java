package project.dao.jdbc;

import project.dao.OrdersDAO;
import project.entities.Orders;

import java.io.Serializable;
import java.util.List;

public class JdbcOrdersDAO implements OrdersDAO {

    private static final String SAVE = "INSERT INTO ORDERS (ORDER_PRICE, USER_ID) VALUES (?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM ORDERS WHERE ID = ?";
    private static final String UPDATE = "UPDATE ORDERS SET ORDER_PRICE = ? AND USER_ID = ? WHERE ID = ? ";
    private static final String DELETE = "DELETE FROM ORDERS WHERE ID = ?";
    private static final String FIND_ALL = "SELECT * FROM ORDERS";


    @Override
    public Object save(Object o) {
        return null;
    }

    @Override
    public Object findByID(Integer id) {
        return null;
    }

    @Override
    public Object update(Integer id) {
        return null;
    }

    @Override
    public Object delete(Integer id) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }
}
