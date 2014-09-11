package net.franckbenault.jdbc.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.franckbenault.jdbc.DBServerInterface;

public class DerbyServer implements DBServerInterface {

	private Connection connection;
	
	public void start() throws ClassNotFoundException, SQLException {
	
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		connection = DriverManager.
		    getConnection("jdbc:derby:memory:testDB;create=true");

	}
	
	public int executeQueryUpdate(String query) throws SQLException {
        PreparedStatement ps2 = connection.prepareStatement(
        		query );
        return ps2.executeUpdate();
	}
	
	public int countTables() throws SQLException {
	
        PreparedStatement ps2 = connection.prepareStatement(
        		"select s.schemaname || '.' || t.tablename from sys.systables t, sys.sysschemas s "
        		+ "where t.schemaid = s.schemaid and t.tabletype = 'T' "
        		+ "order by s.schemaname, t.tablename" );
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
        return size;

	}
	
	public void stop() throws SQLException {
		connection.close();
	}
}
