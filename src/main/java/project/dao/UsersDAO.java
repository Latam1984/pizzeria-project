package project.dao;

import project.entities.Users;

/**
 * The interface extends methods from GenericDAO for working with data base and entity
 * The interface will be implemented by DAO classes that will work with Users entity
 *
 * @author Andrey
 */

public interface UsersDAO extends GenericDAO<Users, Integer> {

}
