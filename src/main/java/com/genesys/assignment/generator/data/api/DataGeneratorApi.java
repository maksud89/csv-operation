/*------------------------------------------------------------------------------
 *******************************************************************************
 * The sole purpose of this program is to solve a quiz asked by Genesys Recruiting Team.
 * No commercial or any other purpose apart from the quiz solve  
 *******************************************************************************
 *----------------------------------------------------------------------------*/

package com.genesys.assignment.generator.data.api;


/**
 * Data Generator Service interface is used to produce mock data to feed into the CSV
 * @author Maksudur Rahman
 *
 */
public interface DataGeneratorApi {
	
	
	/**
	 * @return An array of random First Name, Last name and Age
	 */
	String[] generateNameAndAge();

}
