/*------------------------------------------------------------------------------
 *******************************************************************************
 * The sole purpose of this program is to solve a quiz asked by Genesys Recruiting Team.
 * No commercial or any other purpose apart from the quiz solve  
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.genesys.assignment.db.parser.csv.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.genesys.assignment.db.parser.csv.api.ParserDao;

/**
 * Implementation of {@link ParserDao}
 * 
 * @author emakrah
 *
 */
public class CsvParserDao implements ParserDao {

	private ConnectionGenerator connection = ConnectionGenerator.getInstance();

	@Override
	public void createTable() {
		String sql = "CREATE TABLE CSV_info (firstname VARCHAR(30),secondname VARCHAR(30),age INTEGER)";
		try {
			Statement st = connection.getConnection().createStatement();
			st.executeUpdate(sql);
			System.out.println("Database table has been created");
		} catch (SQLException e) {
			System.out.println("Database exception during table creation: " + e.getMessage());
		}

	}

	@Override
	public void insertData(String fileLocation) {
		BufferedReader fileReader = null;
		String line = "";
		String sql = "insert into CSV_info values (?,?,?)";
		try {
			fileReader = new BufferedReader(new FileReader(fileLocation));
			fileReader.readLine();
			PreparedStatement ps = connection.getConnection().prepareStatement(sql);

			while ((line = fileReader.readLine()) != null) {
				String[] tokens = line.split(",");
				if (tokens.length > 0) {
					ps.setString(1, tokens[0]);
					ps.setString(2, tokens[1]);
					ps.setInt(3, Integer.valueOf(tokens[2]));
					ps.executeUpdate();
				}

			}
			System.out.println("CSV file has been parsed and data has been inserted into the Database");
		} catch (FileNotFoundException e) {
			System.out.println("File was not found in insert operation: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Inserting Data into DB: I/O exception: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Database exception during data insertion: " + e.getMessage());
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileReader!!!" + e.getMessage());
			}
		}

	}
}