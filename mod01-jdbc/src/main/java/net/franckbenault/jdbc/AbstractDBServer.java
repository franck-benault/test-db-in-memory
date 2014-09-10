package net.franckbenault.jdbc;

import java.sql.SQLException;

public interface AbstractDBServer {

	void start() throws ClassNotFoundException, SQLException;
	
	int executeQueryUpdate(String query) throws SQLException;
	
	int countTables() throws SQLException;
	
	void stop() throws SQLException;
}
