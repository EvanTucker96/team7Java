/*
Bilal Ahmad
Date: April 17, 2020
Purpose: to connect to Database via the connector
 */
package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    //connection string for the database
    private static final  String connectionURL = "jdbc:mysql://localhost:3306/travelexperts";

    //connection to db
    public static Connection connectToDB() {
        try {
            //"com.mysql.jdbc.Driver.   oracle.jdbc.driver.OracleDriver"
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }

        //user credentials
        String USER = "root";
        String PASS = "";

        //driver to connect to the MYSQL database
        try {
            return DriverManager.getConnection(connectionURL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
