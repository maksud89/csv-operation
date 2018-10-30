package com.genesys.assignment.generator.service;

/**
 * This class contain the main method to run the program 
 * @author emakrah
 *
 */
public class GeneratorAndParserProvider {

	public static void main(String[] args) {
		InputProcessAndValidation ipv = new InputProcessAndValidation();
		ipv.processAndValidate(args);

	}

}
