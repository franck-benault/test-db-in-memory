package net.franckbenault.jpa.hibernate;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import net.franckbenault.jpa.hibernate.impl.StudentManagerImpl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class StudentManagerTest {

	private static StudentManager studentManager;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		studentManager = new StudentManagerImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private int countStudentsJDBC() throws ClassNotFoundException, SQLException {
		int counter = 0;
		Class.forName("org.hsqldb.jdbcDriver");
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:testdb",
				"sa", "");
		Statement st = conn.createStatement();

		ResultSet mrs = conn.getMetaData().getTables(null, null, null,
				new String[] { "TABLE" });
		while (mrs.next()) {
			String tableName = mrs.getString(3);
			System.out.println("\n\n\n\nTable Name: " + tableName);

			ResultSet rs = st.executeQuery("select * from " + tableName);
			//ResultSetMetaData metadata = rs.getMetaData();
			while (rs.next()) {
				counter++;
			}
		}

		return counter;
	}

	@Test
	public void testCreateStudent() throws ClassNotFoundException, SQLException {

		int countBefore = countStudentsJDBC();
		studentManager.createStudent(new Student());
		int countAfter = countStudentsJDBC();

		assertEquals(countBefore + 1, countAfter);
	}

	@Test
	public void testRemoveStudent() throws ClassNotFoundException, SQLException {
		int countBefore = countStudentsJDBC();

		Student student = studentManager.createStudent(new Student());

		studentManager.removeStudent(student);
		int countAfter = countStudentsJDBC();

		assertEquals(countBefore, countAfter);
	}

}
