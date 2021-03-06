package net.franckbenault.jdbc;

import java.sql.SQLException;

public interface DBServerInterface {

	void start() throws ClassNotFoundException, SQLException;
	
	int executeQueryUpdate(String query) throws SQLException;
	
	int executeQueryCount(String query) throws SQLException;		 
	
	void stop() throws SQLException;
}
