package net.franckbenault.jdbc.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.franckbenault.jdbc.AbstractDBServer;

public class H2Server implements AbstractDBServer {

	private Connection connection;
	
	public void start() throws ClassNotFoundException, SQLException {
	
        Class.forName("org.h2.Driver");
        connection = DriverManager.
            getConnection("jdbc:h2:~/test", "sa", "");

	}
	
	public int executeQueryUpdate(String query) throws SQLException {
        PreparedStatement ps2 = connection.prepareStatement(
        		query );
        return ps2.executeUpdate();
	}
	
	public int countTables() throws SQLException {
	
        PreparedStatement ps2 = connection.prepareStatement(
        		"Show tables;" );
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
