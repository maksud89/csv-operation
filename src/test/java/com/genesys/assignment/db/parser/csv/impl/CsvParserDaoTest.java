package com.genesys.assignment.db.parser.csv.impl;

import static org.junit.Assert.assertTrue;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.genesys.assignment.db.parser.csv.api.ParserDao;

public class CsvParserDaoTest {

	private ParserDao dao;
	private static final String JDBC_URL = "jdbc:derby:target/test_db;create=true";
	private static final String SECOND_JDBC_URL = "jdbc:derby:target/test_db_1;create=true";
	private static final String FILE_PATH = "target/test_file.csv";
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER = "firstName,lastName,age";

	@Before
	public void setUp() {
		dao = new CsvParserDao();
	}

	@Test
	public void testCreateTable() throws SQLException {
		ConnectionGenerator.getInstance().createConnection(JDBC_URL);
		dao.createTable();
		Connection conn = ConnectionGenerator.getInstance().getConnection();
		DatabaseMetaData dbMetaData = conn.getMetaData();
		ResultSet rs = dbMetaData.getTables(null, null, "%", null);
		List<String> tables = new ArrayList<String>();
		while (rs.next()) {
			tables.add(rs.getString(3));

		}
		assertTrue(tables.contains("CSV_INFO"));
		ConnectionGenerator.getInstance().releaseConnection();

	}

	@Test
	public void testInsertData() throws SQLException, IOException {
		generateCsv(FILE_PATH);
		ConnectionGenerator.getInstance().createConnection(SECOND_JDBC_URL);
		dao.createTable();
		dao.insertData(FILE_PATH);
		Connection conn = ConnectionGenerator.getInstance().getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM CSV_INFO");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		List<String> db_data = new ArrayList<String>();

		while (rs.next() != false) {
            for (int i = 1; i <= columnCount; i++) {
            	db_data.add(rs.getObject(i).toString());
            }
        }
		
		assertTrue(db_data.contains("Maksudur"));
		assertTrue(db_data.contains("Rahman"));
		assertTrue(db_data.contains("35"));
		ConnectionGenerator.getInstance().releaseConnection();

	}

	private void generateCsv(String filePath) throws IOException {

		FileWriter fileWriter = null;
		fileWriter = new FileWriter(FILE_PATH);
		fileWriter.append(FILE_HEADER.toString());
		fileWriter.append(NEW_LINE_SEPARATOR);
		
		fileWriter.append("Maksudur");
		fileWriter.append(COMMA_DELIMITER);
		fileWriter.append("Rahman");
		fileWriter.append(COMMA_DELIMITER);
		fileWriter.append("35");
		fileWriter.append(NEW_LINE_SEPARATOR);
		fileWriter.close();

	}

}
