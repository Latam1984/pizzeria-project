package project.dao;

import project.entities.Pizza;

/**
 * The interface extends methods from GenericDAO for working with data base and entity
 * The interface will be implemented by DAO classes that will work with Pizza entity
 *
 * @author Andrey
 */
public interface PizzaDAO extends GenericDAO<Pizza, Integer>{
    
}
