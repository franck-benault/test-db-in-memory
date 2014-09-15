package net.franckbenault.jdbc.hsqldb;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.franckbenault.jdbc.DBServerInterface;


public class HSQLDBServer implements DBServerInterface {

	private Connection connection;
	
	public void start() throws ClassNotFoundException, SQLException {
	
        Class.forName("org.hsqldb.jdbcDriver");
        connection = DriverManager.
            getConnection("jdbc:hsqldb:mem:testdb", "sa", "");

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

	public int countConstraints() throws SQLException {
		
		String query ="SELECT CONSTRAINT_NAME, TABLE_NAME, TABLE_SCHEMA, TABLE_CATALOG FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS where CONSTRAINT_SCHEMA='PUBLIC'";
		PreparedStatement ps2 = connection.prepareStatement(
        		query );
		ResultSet rs =  ps2.executeQuery();
      
        int size = 0;
        while(rs.next()){
            	System.out.println("..."+rs.getString(1)+"..."+rs.getString(2)+"..."+rs.getString(3)+"..."+rs.getString(4) );
                size++;
           }
		return size;
	}

	public String getDBVersion() throws SQLException {
		DatabaseMetaData md = connection.getMetaData();
		return md.getDatabaseProductVersion();
	}
}
