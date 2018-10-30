package com.genesys.assignment.db.parser.csv.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class ConnectionGeneratorTest {
	private static final String JDBC_URL = "jdbc:derby:target/test_db_table;create=true";

	@Test
	public void testGetInstance() {
		assertNotNull(ConnectionGenerator.getInstance());
	}

	@Test
	public void testGetConnection() {
		ConnectionGenerator.getInstance().createConnection(JDBC_URL);
		Connection conn = ConnectionGenerator.getInstance().getConnection();
		assertNotNull(conn);
		ConnectionGenerator.getInstance().releaseConnection();
	}

	@Test
	public void testCloseConnection() throws SQLException {
		ConnectionGenerator.getInstance().createConnection(JDBC_URL);
		Connection conn = ConnectionGenerator.getInstance().getConnection();
		assertFalse(conn.isClosed());
		ConnectionGenerator.getInstance().releaseConnection();
		assertTrue(conn.isClosed());

	}

}
