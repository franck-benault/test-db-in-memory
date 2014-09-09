package net.franckbenault.jdbc.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestHSQLDB {

    public static void main(String[] a)
            throws Exception {
        Class.forName("org.hsqldb.jdbcDriver");
        Connection conn = DriverManager.
            getConnection("jdbc:hsqldb:mem:testdb", "sa", "");
        System.out.println("hsqldb open");
        // add application code here
        conn.close();
    }
}
