package net.franckbenault.jdbc.derby;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DerbyServerTest {
	
	private static DerbyServer server;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		server = new DerbyServer();
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

}
