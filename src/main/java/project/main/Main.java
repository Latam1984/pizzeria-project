package project.main;


import project.connections.ConnectionDB;
import project.connections.ConnectionMySQL;
import project.dao.jdbc.JdbcComponentsDAO;

import java.sql.Connection;
import java.sql.SQLException;

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
        System.out.println(jdbcComponentsDAO.findByName("СОЛЬ"));



    }
}
