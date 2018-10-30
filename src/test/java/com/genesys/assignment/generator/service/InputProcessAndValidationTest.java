package com.genesys.assignment.generator.service;

import org.junit.Before;
import org.junit.Test;


public class InputProcessAndValidationTest {
	private InputProcessAndValidation process;
	
	@Before
	public void setUp() {
		process = new InputProcessAndValidation();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void processAndValidateTestWithMoreThan2Args() {
		String[] args = {"Operation_type","File","invalid_arg"};
		process.processAndValidate(args);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void processAndValidateTestWithLessThan2Args() {
		String[] args = {"Operation_type"};
		process.processAndValidate(args);
	}
	
	//1st argument neither -generate nor parse
	@Test(expected=IllegalArgumentException.class)
	public void triggerOperationWithWrongOperationType1() {
		String[] args = {"-wrong operation type","target/mak.csv -100"};
		process.processAndValidate(args);
	}
	
	//Hyphen(-) missing before generate
	@Test(expected=IllegalArgumentException.class)
	public void triggerOperationWithWrongOperationType2() {
		String[] args = {"generate","target/mak.csv -100"};
		process.processAndValidate(args);
	}
	
	//Hyphen(-) missing before parse
	@Test(expected=IllegalArgumentException.class)
	public void triggerOperationWithWrongOperationType3() {
		String[] args = {"parse","target/mak.csv -100"};
		process.processAndValidate(args);
	}
	

	//Number of lines out of boundary (more than 9999)
	@Test(expected=IllegalArgumentException.class)
	public void triggerOperationWithWrongOperationType4() {
		String[] args = {"-generate","target/mak.csv -10000"};
		process.processAndValidate(args);
	}

}
