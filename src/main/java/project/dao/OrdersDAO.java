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

public interface OrdersDAO <Orders, ID> extends Serializable  {

    /**
     * Method for saving a new entity in a database
     *
     * @param orders an entity for saving in a database
     * @return id of saved entity
     */
    ID save (Orders orders);

    Orders findByID (Integer id);

    ID update (Integer id);

    ID delete (Integer id);

    List<Orders> findAll ();

}
