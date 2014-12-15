package net.franckbenault.jdbc.h2;

import static org.junit.Assert.*;

import java.sql.SQLException;

import net.franckbenault.jdbc.DBServerInterface;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class H2ServerTest {
	
	private static DBServerInterface server;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		server = new H2Server();
		server.start();
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		server.stop();
	}


	
	@Test
	public void testH2Queries() throws SQLException {
		
		String request = "CREATE TABLE PERSON"+
			    "(PERSON_ID INT NOT NULL, FIRSTNAME VARCHAR(25),"+
				"LASTNAME VARCHAR(25), CONSTRAINT PID PRIMARY KEY (PERSON_ID))";

		server.executeQueryUpdate(request);

		String request2= "Insert into PERSON(PERSON_ID, FIRSTNAME, LASTNAME) values(1,'F1','L1')";
		server.executeQueryUpdate(request2);

		String request3= "select count(*) from PERSON";
		int res =server.executeQueryCount(request3);
		assertEquals(res,1);
		
		String request4= "delete from PERSON where PERSON_ID in (1,2) limit 2";
		res = server.executeQueryUpdate(request4);
		assertEquals(res,1);
		
		String request5 = "DROP TABLE PERSON";
		server.executeQueryUpdate(request5);
	}
	
	



}
