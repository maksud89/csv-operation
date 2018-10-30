/*------------------------------------------------------------------------------
 *******************************************************************************
 * The sole purpose of this program is to solve a quiz asked by Genesys Recruiting Team.
 * No commercial or any other purpose apart from the quiz solve  
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.genesys.assignment.generator.csv.api;

/**
 * CSV generator service Interface is used to generates CSV based on the user
 * input
 * 
 * @author emakrah
 *
 */
public interface CsvGeneratorApi {

	/**
	 * @param path
	 *            A valid path where user want to save the CSV file. Path will
	 *            include the CSV file name. &lt;file_path&gt;/&lt;csv_file_name&gt;
	 *            e.g- C:/Users/mak/Downloads/mycsv1.csv
	 * @param numOfLines
	 *            A valid integer number from 2 to 9999 This number will represent
	 *            how many lines of information want to save into the csv
	 */
	void generateCsv(String path, int numOfLines);

}
