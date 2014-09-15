package net.franckbenault.jdbc;

import java.sql.SQLException;

public interface DBServerInterface {

	void start() throws ClassNotFoundException, SQLException;
	
	int executeQueryUpdate(String query) throws SQLException;
	
	int countTables() throws SQLException;
	
	int countConstraints() throws SQLException;
	
	String getDBVersion() throws SQLException;
	
	void stop() throws SQLException;
}
