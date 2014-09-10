package net.franckbenault.jdbc.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestDerby {

    public static void main(String[] a)
            throws Exception {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection conn = DriverManager.
            getConnection("jdbc:derby:memory:testDB;create=true");
        System.out.println("derby open");

        PreparedStatement ps2 = conn.prepareStatement(
        		"select s.schemaname || '.' || t.tablename from sys.systables t, sys.sysschemas s "
        		+ "where t.schemaid = s.schemaid and t.tabletype = 'T' "
        		+ "order by s.schemaname, t.tablename" );
        ResultSet rs = ps2.executeQuery();
        System.out.println(rs);
        
        conn.close();
    }
}
