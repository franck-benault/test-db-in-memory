package net.franckbenault.jpa.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AbstractTester {

	
	private Connection getConnection(String databaseName) throws ClassNotFoundException, SQLException {
		Connection conn =null;
		if(databaseName.equals("hsqldb")) {
			Class.forName("org.hsqldb.jdbcDriver");
			conn = DriverManager.getConnection("jdbc:hsqldb:mem:testdb",
				"sa", "");
		}
		if(databaseName.equals("derby")) {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			conn = DriverManager.
			    getConnection("jdbc:derby:memory:testDB;create=true");
		}
		if(databaseName.equals("h2")) {
	        Class.forName("org.h2.Driver");
	        conn = DriverManager.
	            getConnection("jdbc:h2:mem:test;MODE=MYSQL", "sa", "");
		}
		return conn;
	}
	
	
	protected int countStudentsJDBC(String databaseName) throws ClassNotFoundException, SQLException {
		int counter = 0;
		Connection conn = getConnection(databaseName);
		Statement st = conn.createStatement();
		ResultSet mrs = conn.getMetaData().getTables(null, null, null,
				new String[] { "TABLE" });
		while (mrs.next()) {
			String tableName = mrs.getString(3);
			//System.out.println("\n\n\n\nTable Name: " + tableName);

			ResultSet rs = st.executeQuery("select * from " + tableName);
			//ResultSetMetaData metadata = rs.getMetaData();
			while (rs.next()) {
				counter++;
			}
		}

		return counter;
	}
}
