package net.franckbenault.jdbc.hsqldb;

import static org.junit.Assert.*;

import java.sql.SQLException;

import net.franckbenault.jdbc.DBServerInterface;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class HSQLDBServerTest {
	
	private static DBServerInterface server;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		server = new HSQLDBServer();
		server.start();
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		server.stop();
	}

	@Test
	public void testCountTables() throws SQLException {
		assertEquals(server.countTables(), 0);
	}
	
	@Test
	public void testCountTablesWithRequest() throws SQLException {
		assertEquals(server.countTables(), 0);
		
		String request = "CREATE TABLE HOTELAVAILABILITY"+
			    "(HOTEL_ID INT NOT NULL, BOOKING_DATE DATE NOT NULL,"+
				"ROOMS_TAKEN INT DEFAULT 0, PRIMARY KEY (HOTEL_ID, BOOKING_DATE))";

		server.executeQueryUpdate(request);
		assertEquals(server.countTables(), 1);
		
		String request2 = "DROP TABLE HOTELAVAILABILITY";
		
		server.executeQueryUpdate(request2);
		assertEquals(server.countTables(), 0);


	}
	

}
