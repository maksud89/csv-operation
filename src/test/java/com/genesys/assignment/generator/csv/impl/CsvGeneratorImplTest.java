package com.genesys.assignment.generator.csv.impl;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.genesys.assignment.generator.csv.api.CsvGeneratorApi;

public class CsvGeneratorImplTest {
	private CsvGeneratorApi generator;
	private static final String FILE_PATH = "target/test_file.csv";
	private static final int NUM_OF_LINES = 50;

	@Before
	public void setUp() {
		generator = new CsvGeneratorImpl();
	}

	@Test
	public void testGenerateCsv() throws IOException {
		generator.generateCsv(FILE_PATH, NUM_OF_LINES);

		BufferedReader br = null;
		int generated_num_lines = 0;

		br = new BufferedReader(new FileReader(FILE_PATH));
		while ((br.readLine()) != null) {
			generated_num_lines++;
		}
		br.close();
		assertEquals(NUM_OF_LINES, generated_num_lines-1);

	}

}
