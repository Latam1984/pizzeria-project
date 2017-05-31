package project.dao.jdbc;

import project.dao.OrdersDAO;
import project.entities.Orders;

import java.util.List;

public class JdbcOrdersDAO implements OrdersDAO {

    private static final String SAVE = "INSERT INTO ORDERS (ORDER_PRICE, USER_ID) VALUES (?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM ORDERS WHERE ID = ?";
    private static final String UPDATE = "UPDATE ORDERS SET ORDER_PRICE = ? AND USER_ID = ? WHERE ID = ? ";
    private static final String DELETE = "DELETE FROM ORDERS WHERE ID = ?";
    private static final String FIND_ALL = "SELECT * FROM ORDERS "



    @Override
    public Integer save(Orders obj) {
        return null;
    }

    @Override
    public Orders findById(Integer integer) {
        return null;
    }

    @Override
    public void update(Orders obj) {
    }

    @Override
    public void delete(Orders obj) {
    }

    @Override
    public List<Orders> findAll() {
        return null;
    }

    @Override
    public Orders findByName(String name) {
        return null;
    }
}
