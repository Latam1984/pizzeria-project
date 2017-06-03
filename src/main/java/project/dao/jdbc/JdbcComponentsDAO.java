package project.dao.jdbc;

import org.hibernate.SessionFactory;
import project.connections.ConnectionDB;
import project.dao.ComponentsDAO;
import project.entities.Components;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JdbcComponentsDAO implements ComponentsDAO {

    /**
     * A pattern of an SQL command (without particular values)
     * for saving a component in a database
     */
    private final static String SAVE = "INSERT INTO components (COMPONENT_NAME, WEIGHT, PRICE) VALUES(?,?,?)";

    /**
     * A pattern of an SQL command (without particular value)
     * for finding a component in a database by id
     */
    private final static String FIND_BY_ID = "SELECT * FROM components WHERE id = ?";

    /**
     * A pattern of an SQL command (without particular values)
     * for update a component in a database
     */
    private final static String UPDATE = "UPDATE components SET  PRICE = ? WHERE COMPONENT_NAME = ?";

    /**
     * A pattern of an SQL command (without particular value)
     * for removing a component from a database by id
     */
    private final static String DELETE = "DELETE FROM components WHERE COMPONENT_NAME = ?";

    /**
     * An SQL command for getting all components from a database
     */
    private final static String FIND_ALL = "SELECT * FROM components";

    /**
     * A pattern of an SQL command (without particular value)
     * for finding a component in a database by name
     */
    private final static String FIND_BY_NAME = "SELECT * FROM components WHERE COMPONENT_NAME = ? ";

    /**
     * A pattern of an SQL command  for finding a id from the last
     * inserted component in a database
     */
    private final static String GET_LAST_INSERTED = "SELECT LAST_INSERT_ID()";

    /**
     * Connection to database
     */
    private ConnectionDB connectionDB;

    /**
     * @param connectionDB a connection to a database
     */
    public JdbcComponentsDAO(ConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

    /**
     * Method saves a new component in database
     *
     * @param component a component for saving in a database
     * @return component id, if the component was saving to database successfully
     */
    @Override
    public Integer save(Components component) {
        Integer id;
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
             Statement statement = connection.createStatement()) {
            preparedStatement.setString(1, component.getComponentName());
            preparedStatement.setBigDecimal(2, component.getWeight());
            preparedStatement.setBigDecimal(3, component.getPrice());
            preparedStatement.executeUpdate();
//            if (findByName(component.getComponentName()) == null){
//
//            }
            ResultSet resultSet = statement.executeQuery(GET_LAST_INSERTED);
            resultSet.next();
            id = resultSet.getInt(1);
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method which find current component by id
     *
     * @param id the id of a component
     * @return a component with entered id
     * or new component with empty parameters if component with this id does not exist
     */
    @Override
    public Components findById(Integer id) {
        Components foundedComponent = new Components(id, "");
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                foundedComponent = new Components(
                        resultSet.getInt("ID"),
                        resultSet.getString("COMPONENT_NAME")
                );
            }
            return foundedComponent;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method updates components in the database
     *
     * @param component a component with new name
     */
    @Override
    public void update(Components component) {
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setBigDecimal(1, component.getPrice());
            preparedStatement.setString(2, component.getComponentName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method removes a component from database
     *
     * @param component component which must be removed
     */
    @Override
    public void delete(Components component) {
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setString(1, component.getComponentName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method returns all components from the database
     *
     * @return list of all components from the database
     */
    @Override
    public List<Components> findAll() {
        List<Components> allComponents = new ArrayList<>();
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                allComponents.add(
                        new Components(
                                resultSet.getInt("id"),
                                resultSet.getString("component_name")
                        )
                );
            }
            return allComponents;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method finds a component in database by it name
     *
     * @param name a name of a component
     * @return a component with entered name
     * or new component with empty parameters if component with this name does not exist
     */
    @Override
    public Components findByName(String name) {
        Components components = new Components(0, name);
        try {
            try (Connection connection = connectionDB.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME)) {
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    components = new Components(
                            resultSet.getInt("id"),
                            resultSet.getString("component_name")
                    );
                }
                return components;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    private boolean

    @Override
    public String toString() {
        return "JdbcComponentsDAO{" +
                "connectionDB=" + connectionDB +
                '}';
    }
}
