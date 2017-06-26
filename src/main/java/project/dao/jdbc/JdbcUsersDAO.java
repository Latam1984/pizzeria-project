package project.dao.jdbc;

import project.connections.ConnectionDB;
import project.dao.UsersDAO;
import project.entities.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

public class JdbcUsersDAO implements UsersDAO {
    /**
     * Logger slf4j. All logs saves in target.jdbc
     */
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JdbcUsersDAO.class);


    /**
     * A pattern of an SQL command (without particular values)
     * for saving an user in a database
     */
    private static final String SAVE = "INSERT INTO USERS (ROLE, LOGIN, PASSWORD) VALUES(?, ?, ?)";

    /**
     * A pattern of an SQL command (without particular value)
     * for finding an user in a database by id
     */
    private static final String FIND_BY_ID = "SELECT * FROM USERS WHERE ID = ?;";

    /**
     * A pattern of an SQL command (without particular value)
     * for update an user in a database by id
     */
    private static final String UPDATE = "UPDATE USERS SET ROLE = ?, LOGIN = ?, PASSWORD = ?   WHERE ID =?, ";

    /**
     * A pattern of an SQL command (without particular value)
     * for delete an user in a database by id
     */
    private static final String DELETE = "DELETE FROM USERS WHERE ID =?";

    /**
     * A pattern of an SQL command (without particular value)
     * for finding all orders in a database by id
     */
    private static final String FIND_ALL = "SELECT * FROM USERS";

    /**
     * A pattern of an SQL command (without particular value)
     * for finding user by id in a database by id
     */
    private static final String FIND_BY_NAME = "SELECT * FROM USERS WHERE NAME = ?";

    /**
     * A pattern of an SQL command  for finding a id from the last
     * inserted user in a database
     */
    private static final String GET_LAST_INSERTED = "SELECT LAST_INSERT_ID";

    /**
     * Connection to database
     */
    private ConnectionDB connectionDB;

    /**
     * @param connectionDB a connection to a database
     */
    public JdbcUsersDAO(ConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

    /**
     * Method saves a new user in database
     *
     * @param user for saving in a database
     * @return user id, if the user was saving to database successfully
     */
    @Override
    public Integer save(Users user) {
        Integer id;
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
        ) {
            LOGGER.info("User saved in DB");
            preparedStatement.setString(1, "ROLE");
            preparedStatement.setString(2, "LOGIN");
            preparedStatement.setString(3, "PASSWORD");
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery(GET_LAST_INSERTED);
            resultSet.next();
            id = resultSet.getInt(1);
            return id;
        } catch (SQLException e) {
            LOGGER.error("User cannot save in DB");
            throw new RuntimeException(e);
        }
    }

    /**
     * Method which find current user by id
     *
     * @param id the id of a user
     * @return a user with entered id
     * or new user with empty parameters if component with this id does not exist
     */
    @Override
    public Users findById(Integer id) {
        Users foundedUser = new Users();
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            LOGGER.info("Looking user with id " + id);
            preparedStatement.setString(1, "ID");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                foundedUser = new Users(
                        resultSet.getInt("ID"),
                        resultSet.getString("ROLE"),
                        resultSet.getString("LOGIN"),
                        resultSet.getString("PASSWORD")
                );
            }
            return foundedUser;
        } catch (SQLException e) {
            LOGGER.error("Cannot find order by id");
            throw new RuntimeException(e);
        }
    }

    /**
     * Method updates user in the database
     *
     * @param user an user with new parameters
     */
    @Override
    public void update(Users user) {
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, "ROLE");
            preparedStatement.setString(2, "LOGIN");
            preparedStatement.setString(3, "PASSWORD");
            preparedStatement.executeUpdate();
            LOGGER.info("User updated ");
        } catch (SQLException e) {
            LOGGER.error("Cannot update user");
            throw new RuntimeException(e);
        }
    }

    /**
     * Method removes an user from database
     *
     * @param user component which must be removed
     */
    @Override
    public void delete(Users user) {
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE ")) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
            LOGGER.info("User deleted");
        } catch (SQLException e) {
            LOGGER.error("Cannot delete user ");
            throw new RuntimeException(e);
        }
    }

    /**
     * Method returns all users from the database
     *
     * @return list of all users from the database
     */
    @Override
    public List<Users> findAll() {
        List<Users> allUsers = new ArrayList<>();
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                allUsers.add(new Users(
                        resultSet.getInt("ID"),
                        resultSet.getString("ROLE"),
                        resultSet.getString("LOGIN"),
                        resultSet.getString("PASSWORD")
                ));
            }
            return allUsers;
        } catch (SQLException e) {
            LOGGER.error("Cannot load all users ");
            throw new RuntimeException(e);
        }
    }

    /**
     * Method finds an user in database by it name
     *
     * @param name a name of a component
     * @return an user with entered name
     * or new user with empty parameters if component with this name does not exist
     */
    @Override
    public Users findByName(String name) {
        Users foundedUsers = new Users();
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME)) {
            preparedStatement.setString(1, "ID");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                foundedUsers = new Users(
                        resultSet.getInt("ID"),
                        resultSet.getString("ROLE"),
                        resultSet.getString("LOGIN"),
                        resultSet.getString("PASSWORD")
                );
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot find user by name ");
            throw new RuntimeException(e);
        }
        return null;
    }

}
