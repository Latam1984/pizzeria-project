package project.main;


import project.connections.ConnectionDB;
import project.connections.ConnectionMySQL;
import project.dao.jdbc.JdbcComponentsDAO;
import project.dao.jdbc.JdbcOrdersDAO;
import project.entities.Orders;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by Aleksey on 26.04.2017.
 */
public class Main {
    public static void main(String[] args) {
        //Verification of existing methods
        ConnectionDB connectionDB = new ConnectionMySQL();
        JdbcComponentsDAO jdbcComponentsDAO = new JdbcComponentsDAO(connectionDB);

        //findAll
        //     jdbcComponentsDAO.findAll().forEach(System.out::println);
        //findById
//        System.out.println(jdbcComponentsDAO.findById(5));
        //findByName
        // System.out.println(jdbcComponentsDAO.findByName("СОЛЬ"));

        JdbcOrdersDAO jdbcOrdersDAO = new JdbcOrdersDAO(connectionDB);
       jdbcOrdersDAO.save(new Orders(5, new Timestamp(System.currentTimeMillis()), new BigDecimal(700), 2));

        //System.out.println(jdbcOrdersDAO.findByID(5));

    }
}
