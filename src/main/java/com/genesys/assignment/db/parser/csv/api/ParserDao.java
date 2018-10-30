/*------------------------------------------------------------------------------
 *******************************************************************************
 * The sole purpose of this program is to solve a quiz asked by Genesys Recruiting Team.
 * No commercial or any other purpose apart from the quiz solve  
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.genesys.assignment.db.parser.csv.api;

/**
 * @author emakrah ParserDao interface used to creating Database table and
 *         insert the data collected from the user defined csv file
 *
 */
public interface ParserDao {

	/**
	 * This method will be used to generate the Database Table
	 */
	void createTable();

	/**
	 * This method will be used to insert content of a user defined csv file into
	 * the database table
	 * 
	 * @param fileLocation
	 *            file path of the CSV file e.g C:\Users\mak\Downloads\test.csv
	 */
	void insertData(String fileLocation);

}
