/*------------------------------------------------------------------------------
 *******************************************************************************
 * The sole purpose of this program is to solve a quiz asked by Genesys Recruiting Team.
 * No commercial or any other purpose apart from the quiz solve  
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.genesys.assignment.generator.csv.impl;

import java.io.FileWriter;
import java.io.IOException;

import com.genesys.assignment.generator.csv.api.CsvGeneratorApi;
import com.genesys.assignment.generator.data.api.DataGeneratorApi;
import com.genesys.assignment.generator.data.impl.DataGeneratorImpl;

/**
 * Implementation of {@link CsvGeneratorApi}
 * 
 * @author emakrah
 *
 */
public class CsvGeneratorImpl implements CsvGeneratorApi {

	private static final String FILE_HEADER = "firstName,lastName,age";
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private DataGeneratorApi generator = new DataGeneratorImpl();

	@Override
	public void generateCsv(String path, int numOfLines) {

		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(path);
			fileWriter.append(FILE_HEADER.toString());
			fileWriter.append(NEW_LINE_SEPARATOR);

			for (int i = 1; i <= numOfLines; i++) {
				String[] data = generator.generateNameAndAge();
				fileWriter.append(data[0]);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(data[1]);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(data[2]);
				fileWriter.append(NEW_LINE_SEPARATOR);

			}
			
			System.out.println("CSV file has been successfully generated");

		} catch (IOException e) {
			System.out.println("Generating CSV: I/O exception: " + e.getMessage());
		} finally {

			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}

		}

	}

}
