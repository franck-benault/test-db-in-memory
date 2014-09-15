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
        
        PreparedStatement ps2 = connection.prepareStatement(
        		"DROP ALL OBJECTS;" );
        ps2.executeUpdate();

	}
	
	public int executeQueryUpdate(String query) throws SQLException {
        PreparedStatement ps2 = connection.prepareStatement(
        		query );
        return ps2.executeUpdate();
	}
	
	public int countTables() throws SQLException {
	
		
		DatabaseMetaData md = connection.getMetaData();
        String[] types = {"TABLE"};
        ResultSet rs = md.getTables(null, null, "%", types);
	
		
        int size = 0;
        while(rs.next()){
            	//System.out.println(rs.getString(3));
                size++;
           }
        

        return size;
	}
	
	public void stop() throws SQLException {
		connection.close();
	}

	public int countConstraints() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getDBVersion() throws SQLException {
		DatabaseMetaData md = connection.getMetaData();
		return md.getDatabaseProductVersion();
	}
}
