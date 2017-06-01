package project.dao;

import project.entities.Orders;

import java.io.Serializable;
import java.util.List;

/**
 * The interface extends methods from GenericDAO for working with data base and entity
 * The interface will be implemented by DAO classes that will work with Orders entity
 *
 * @author Andrey
 */

public interface OrdersDAO<T, ID extends Serializable> {

    /**
     * Method for saving a new entity in a database
     *
     * @param orders an entity for saving in a database
     * @return id of saved entity
     */
    ID save(T orders);

    /**
     * Method for finding entity in a database by id
     *
     * @param id the id of an entity
     * @return founded entity
     */
    Orders findByID(ID id);

    /**
     * Method for updating entity in a database
     *
     * @param order an entity with new parameters for updating
     * @return ID of updated entity
     */
    ID update(T order);

    /**
     * Method for deleting entity in a database
     *
     * @param order an entity for delete from DB
     * @return ID of the deleted entity
     */
    ID delete(T order);

    /**
     * Method for getting all entities of a certain type
     *
     * @return list of entities
     */
    List<T> findAll();

}
