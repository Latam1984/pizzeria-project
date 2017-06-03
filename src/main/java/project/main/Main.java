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

        /*
        test orders
         */


        JdbcOrdersDAO jdbcOrdersDAO = new JdbcOrdersDAO(connectionDB);
        //test method save
        //jdbcOrdersDAO.save(new Orders(5, new Timestamp(System.currentTimeMillis()), new BigDecimal(700)));

        //test method findByID
        //System.out.println(jdbcOrdersDAO.findByID(5));

        //test method update DO NOT WORK
        jdbcOrdersDAO.update(new Orders(2,new BigDecimal(111)));

        //test method update -- WORK--
        //jdbcOrdersDAO.delete(new Orders(1, new Timestamp(System.currentTimeMillis()), new BigDecimal(111)));



    }
}
