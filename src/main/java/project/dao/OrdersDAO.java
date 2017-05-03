package project.dao;

import project.entities.Orders;

/**
 * The interface extends methods from GenericDAO for working with data base and entity
 * The interface will be implemented by DAO classes that will work with Orders entity
 *
 * @author Andrey
 */

public interface OrdersDAO extends GenericDAO<Orders, Integer> {
}
