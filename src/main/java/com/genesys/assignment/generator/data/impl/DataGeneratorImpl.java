/*------------------------------------------------------------------------------
 *******************************************************************************
 * The sole purpose of this program is to solve a quiz asked by Genesys Recruiting Team.
 * No commercial or any other purpose apart from the quiz solve  
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.genesys.assignment.generator.data.impl;

import com.genesys.assignment.generator.data.api.DataGeneratorApi;
import com.github.javafaker.Faker;

/**
 * Implementation of {@link DataGeneratorApi}
 * Faker 3pp has been used to generate fake Names and Age to feed to the CSV 
 * @author emakrah
 *
 */
public class DataGeneratorImpl implements DataGeneratorApi {
	private Faker faker = new Faker();
	private String[] info = new String[3];

	
	
	@Override
	public String[] generateNameAndAge() {
		info[0] = faker.name().firstName();
		info[1] = faker.name().lastName();
		info[2] = Integer.toString(faker.number().numberBetween(2, 99));
		return info;
	}

}
