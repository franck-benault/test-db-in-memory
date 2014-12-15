package net.franckbenault.jdbc.h2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.franckbenault.jdbc.DBServerInterface;

public class H2Server implements DBServerInterface {

	private Connection connection;
	
	public void start() throws ClassNotFoundException, SQLException {
	
        Class.forName("org.h2.Driver");
        connection = DriverManager.
            getConnection("jdbc:h2:mem:test", "sa", "");
            //getConnection("jdbc:h2:tcp://localhost/~/test;MODE=MySQL", "sa", "");

        		
        		
        PreparedStatement ps2 = connection.prepareStatement(
        		"DROP ALL OBJECTS;" );
        ps2.executeUpdate();

	}
	
	public int executeQueryUpdate(String query) throws SQLException {
        PreparedStatement ps2 = connection.prepareStatement(
        		query );
        return ps2.executeUpdate();
	}
	

	public int executeQueryCount(String query) throws SQLException {
        PreparedStatement ps2 = connection.prepareStatement(
        		query );
        ResultSet rs= ps2.executeQuery();
        rs.next();
        return rs.getInt(1);
	}
	
	public void stop() throws SQLException {
		connection.close();
	}

}
