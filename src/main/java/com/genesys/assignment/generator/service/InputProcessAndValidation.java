/*------------------------------------------------------------------------------
 *******************************************************************************
 * The sole purpose of this program is to solve a quiz asked by Genesys Recruiting Team.
 * No commercial or any other purpose apart from the quiz solve  
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.genesys.assignment.generator.service;

import com.genesys.assignment.db.parser.csv.api.ParserDao;
import com.genesys.assignment.db.parser.csv.impl.ConnectionGenerator;
import com.genesys.assignment.db.parser.csv.impl.CsvParserDao;
import com.genesys.assignment.generator.csv.api.CsvGeneratorApi;
import com.genesys.assignment.generator.csv.impl.CsvGeneratorImpl;

/**
 * This class is responsible to validate user input and also to process the user
 * input
 * 
 * @author emakrah
 *
 */
public class InputProcessAndValidation {
	private static final int MAX_LINE = 9999;
	private static final int MIN_LINE = 2;

	/**
	 * For this program to run, It has to run with exactly 2 inputs for both CSV
	 * generator and also for csv parsing. 1st input is for operation type- either
	 * -generate (to generate CSV) or -parse (to parse CSV) 2nd input is dependent
	 * on the operation type. Every operation type has also 2 inputs. They have to
	 * pass inside double quote("") and separated by a space followed by a hyphen
	 * (-)
	 * 
	 * Format for generating CSV: -generate "&lt;file_path&gt;/&lt;csv_file_name&gt;
	 * -&lt;number_of_lines&gt;" format for parsing CSV: -parse
	 * "&lt;file_path&gt;/&lt;DB_name&gt; -&lt;file_path&gt;/&lt;csv_file_name&gt;"
	 * e.g - for generating CSV: -generate "C:\Users\emak\Downloads\mak4.csv -100"
	 * for parsing CSV: -parse "C:/Users/mak/Downloads/MyDB
	 * -C:\Users\emak\Downloads\mak4.csv"
	 * 
	 * @param args
	 *            user input e.g. -generate "C:\Users\emak\Downloads\mak4.csv -100"
	 *            -parse "C:/Users/mak/Downloads/MyDB
	 *            -C:\Users\emak\Downloads\mak4.csv"
	 */
	public void processAndValidate(String[] args) {
		if (args.length != 2) {
			throw new IllegalArgumentException(getIllegalArgumentMessage());
		}
		triggerOperation(args[0], args[1]);
	}

	private void triggerOperation(String operationType, String input) {

		CsvGeneratorApi generator = new CsvGeneratorImpl();
		if ("-generate".equals(operationType) && input != null) {
			String[] params = processInput(input);
			String path = params[0];
			int numOflines = Integer.parseInt(params[1]);
			if (numOflines < MIN_LINE || numOflines > MAX_LINE) {
				throw new IllegalArgumentException(
						"Number of Lines should be between " + MIN_LINE + " and " + MAX_LINE);
			}
			generator.generateCsv(path, numOflines);
		} else if ("-parse".equals(operationType) && input != null) {

			String[] params = processInput(input);
			triggerDbOperation(params[0], params[1]);
		} else {
			throw new IllegalArgumentException(getIllegalArgumentMessage());
		}

	}

	private void triggerDbOperation(String dbPath, String filePath) {
		ParserDao dao = new CsvParserDao();
		String dbUrl = processDbPath(dbPath);
		ConnectionGenerator.getInstance().createConnection(dbUrl);
		dao.createTable();
		dao.insertData(filePath);
		ConnectionGenerator.getInstance().releaseConnection();
	}

	/**
	 * As we are using, Derby file system Database, user need to pass only location
	 * of the DB with name, this method will internally form the URL string
	 * 
	 * @param dbPath
	 *     Path the DB with DB name 
	 *     e.g C:/Users/mak/Downloads/MyDB 
	 * 
	 * @return full url for the Derby File system Database
	 */
	private String processDbPath(String dbPath) {
		return "jdbc:derby:" + dbPath + ";create=true";
	}

	private String[] processInput(String input) {
		String[] split = input.split(" -");
		if (split.length != 2) {
			throw new IllegalArgumentException(getIllegalArgumentMessage());
		}
		String[] params = { split[0], split[1] };
		return params;

	}

	private String getIllegalArgumentMessage() {

		return "For this program to run, It has to run with exactly 2 inputs for both CSV\r\n"
				+ "generator and also for csv parsing. 1st input is for operation type- either\r\n"
				+ "-generate (to generate CSV) or -parse (to parse CSV) 2nd input is dependent\r\n"
				+ "on the operation type. Every operation type has also 2 inputs. They have to\r\n"
				+ "pass inside double quote(\"\") and separated by a space followed by a hyphen\r\n" + "(-)";
	}

}
