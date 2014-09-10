package net.franckbenault.jdbc.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestH2 {

    public static void main(String[] a)
            throws Exception {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.
            getConnection("jdbc:h2:~/test", "sa", "");
        System.out.println("h2 open");
        
        PreparedStatement ps2 = conn.prepareStatement("Show tables;");
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
