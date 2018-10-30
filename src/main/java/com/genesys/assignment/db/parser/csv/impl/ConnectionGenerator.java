/*------------------------------------------------------------------------------
 *******************************************************************************
 * The sole purpose of this program is to solve a quiz asked by Genesys Recruiting Team.
 * No commercial or any other purpose apart from the quiz solve  
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.genesys.assignment.db.parser.csv.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * To make the program light weight and simple, we have used file system derby
 * database This is the singleton class to create, get and close the Database
 * Connection
 * 
 * @author emakrah
 *
 */
public class ConnectionGenerator {

	private static ConnectionGenerator connectionInstance;
	private static Connection conn;

	private ConnectionGenerator() {
	}

	/**
	 * @return connectionInstance Return instance of ConnectionGenerator class
	 */
	public static ConnectionGenerator getInstance() {
		if (connectionInstance == null) {
			connectionInstance = new ConnectionGenerator();
		}
		return connectionInstance;
	}

	/**
	 * Create connection based on the URL provided
	 * 
	 * @param dbUrl
	 *            URL of the database
	 */
	public void createConnection(String dbUrl) {
		try {
			conn = DriverManager.getConnection(dbUrl);
		} catch (SQLException e) {
			System.out.println("Database exception during database connection creation: " + e.getMessage());
		}
	}

	/**
	 * Release the DB connection
	 */
	public void releaseConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex) {
				System.out.println("Exception closing connection: " + ex);
			}
		}
	}

	/**
	 * @return Instance of Connection
	 */
	public Connection getConnection() {
		return conn;
	}

}
