package net.franckbenault.jdbc.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestHSQLDB {

    public static void main(String[] a)
            throws Exception {
        Class.forName("org.hsqldb.jdbcDriver");
        Connection conn = DriverManager.
            getConnection("jdbc:hsqldb:mem:testdb", "sa", "");
        System.out.println("hsqldb open");

        PreparedStatement ps2 = conn.prepareStatement(
        		"SELECT * FROM INFORMATION_SCHEMA.SYSTEM_TABLES where TABLE_TYPE='TABLE'" );
        ResultSet rs = ps2.executeQuery();

        int size = 0;
        try {
            while(rs.next()){
                size++;
            }
        }
        catch(Exception ex) {
            System.out.println("------------------Tablerize.getRowCount-----------------");
            System.out.println("Cannot get resultSet row count: " + ex);
            System.out.println("--------------------------------------------------------");
        }
        
        System.out.println("Resultset size "+size);
  
        
        conn.close();
    }
}
