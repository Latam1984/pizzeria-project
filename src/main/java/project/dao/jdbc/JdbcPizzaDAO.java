package project.dao.jdbc;

import project.connections.ConnectionDB;
import project.dao.PizzaDAO;
import project.entities.Pizza;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPizzaDAO implements PizzaDAO {

    /**
     * A pattern  of an SQL command (without particular values)
     * for saving a pizza in a database
     */
    private static final String SAVE = "INSERT INTO pizza (PIZZA_NAME, PIZZA_PRICE) VALUES (?, ?)";

    /**
     * A pattern of an SQL command (without particular value)
     * for finding a pizza in a database by id
     */
    private static final String FIND_BY_ID = "SELECT * FROM pizza WHERE id = ?";

    /**
     * A pattern of an SQL command (without particular values)
     * for update a pizza in a database
     */
    private final static String UPDATE = "UPDATE pizza SET  PIZZA_PRICE = ? WHERE PIZZA_NAME = ?";

    /**
     * A pattern of an SQL command (without particular value)
     * for removing a pizza from a database by id
     */
    private final static String DELETE = "DELETE FROM pizza WHERE PIZZA_NAME = ?";

    /**
     * An SQL command for getting all pizzas from a database
     */
    private final static String FIND_ALL = "SELECT * FROM pizza";

    /**
     * A pattern of an SQL command (without particular value)
     * for finding a pizza in a database by name
     */
    private final static String FIND_BY_NAME = "SELECT * FROM pizza WHERE PIZZA_NAME = ? ";

    /**
     * A pattern of an SQL command  for finding a id from the last
     * inserted pizza in a database
     */
    private final static String GET_LAST_INSERTED = "SELECT LAST_INSERT_ID()";

    /**
     * Connection to database
     */
    private ConnectionDB connectionDB;

    /**
     * @param connectionDB a connection to a database
     */
    public JdbcPizzaDAO(ConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

    /**
     * Method saves a new component in database
     *
     * @param pizza a pizza for saving in a database
     * @return pizza id, if the pizza was saving to database successfully
     */
    @Override
    public Integer save(Pizza pizza) {
        Integer id;
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
             Statement statement = connection.createStatement()) {
            preparedStatement.setString(1, pizza.getPizzaName());
            preparedStatement.setBigDecimal(2, pizza.getPizzaPrice());
            ResultSet resultSet = statement.executeQuery(GET_LAST_INSERTED);
            resultSet.next();
            id = resultSet.getInt(1);
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method which find current pizza by id
     *
     * @param id the id of a pizza
     * @return a pizza with entered id
     * or new pizza with empty parameters if pizza with this id does not exist
     */
    @Override
    public Pizza findById(Integer id) {
        Pizza foundedPizza = new Pizza(id, "", new BigDecimal(0));
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                foundedPizza = new Pizza(
                        resultSet.getInt("ID"),
                        resultSet.getString("PIZZA_NAME"),
                        resultSet.getBigDecimal("PIZZA_PRICE")
                );
            }
            return foundedPizza;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method updates pizza in the database
     *
     * @param pizza a pizza with new name
     */
    @Override
    public void update(Pizza pizza) {
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setBigDecimal(1, pizza.getPizzaPrice());
            preparedStatement.setString(2, pizza.getPizzaName());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method removes a pizza from database
     *
     * @param pizza pizza which must be removed
     */
    @Override
    public void delete(Pizza pizza) {
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setString(1, pizza.getPizzaName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method returns all pizzas from the database
     *
     * @return list of all pizzas from the database
     */
    @Override
    public List<Pizza> findAll() {
        List<Pizza> allPizzas = new ArrayList<>();
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                allPizzas.add(
                        new Pizza(resultSet.getInt("ID"),
                                resultSet.getString("PIZZA_NAME"),
                                resultSet.getBigDecimal("PIZZA_PRICE"))
                );
            }
            return allPizzas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method finds a pizza in database by it name
     *
     * @param name a name of a pizza
     * @return a pizza with entered name
     * or new pizza with empty parameters if pizza with this name does not exist
     */
    @Override
    public Pizza findByName(String name) {
        Pizza pizza = new Pizza(0, name);
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                pizza = new Pizza(
                        resultSet.getInt("ID"),
                        resultSet.getString("PIZZA_NAME")
                );
            }
            return pizza;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String toString() {
        return "JdbcPizzaDAO{" +
                "connectionDB=" + connectionDB +
                '}';
    }
}
