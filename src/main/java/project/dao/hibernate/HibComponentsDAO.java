package project.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import project.dao.ComponentsDAO;
import project.entities.Components;

import java.util.ArrayList;
import java.util.List;

/**
 *  The class implements a set of methods for working with
 * database including Hibernate framework, with Components entity
 */

public class HibComponentsDAO implements ComponentsDAO{

    /**
     * An instance of SessionFactory
     */
    private SessionFactory sessionFactory;

    /**
     * Constructor
     *
     * @param sessionFactory an instance of SessionFactory
     */
    public HibComponentsDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * The method saves a new component in a database
     *
     * @param component a component, which must be save in a database
     * @return components id if a component was add to database successfully
     */
    @Override
    public Integer save(Components component) {
        Integer id = null;
        try (Session session = sessionFactory.openSession()) {
            id = (Integer) session.save(component);
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to save component " + component);
            e.printStackTrace();
        }
        return id;
    }

    /**
     * The method finds a component in database by id of the components
     *
     * @param id an id of a component
     * @return a component with entered id
     * or new component with empty parameters if component with this id does not exist in the database
     */
    @Override
    public Components findById(Integer id) {
        Components component = new Components(id, "");
        try (Session session = sessionFactory.openSession()) {
            Components componentFromDB = session.get(Components.class, id);
            if (componentFromDB != null) {
                component = componentFromDB;
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to find component with id: " + id);
            e.printStackTrace();
        }
        return component;
    }

    /**
     * The method updates a component in a database
     * (finds component in a database by id and overwrites other fields)
     *
     * @param component is a component with new parameters
     */
    @Override
    public void update(Components component) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Components componentFromDb = session.get(Components.class, component.getId());
            if (componentFromDb == null) {
                return;
            }
            componentFromDb.setComponentName(component.getComponentName());
            session.update(componentFromDb);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to update component " + component);
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /**
     * The method removes a component from a database
     *
     * @param component is a component which must be removed
     */
    @Override
    public void delete(Components component) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Components componentFromDb = session.get(Components.class, component.getId());
            if (componentFromDb == null) {
                return;
            }
            session.delete(componentFromDb);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to delete component " + component);
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /**
     * The method returns all components from a database
     *
     * @return list of all components from a database
     */
    @Override
    public List<Components> findAll() {
        List<Components> companies = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            companies = session.createQuery("from Components").list();
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to find all components");
            e.printStackTrace();
        }
        return companies;
    }

    /**
     * Method finds a component in a database by name of the component
     *
     * @param name is a name of a component
     * @return a component with entered name
     * or new component with empty parameters if component with this name does not exist in the database
     */
    @Override
    public Components findByName(String name) {
        Components component = new Components(0, name);
        try (Session session = sessionFactory.openSession()) {
            List<Components> components = session.createQuery("select c from Components c where c.name like :name")
                    .setParameter("name", name).list();
            if (components.size() != 0) {
                component = components.get(0);
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to find component with name: " + name);
            e.printStackTrace();
        }
        return component;
    }
}
