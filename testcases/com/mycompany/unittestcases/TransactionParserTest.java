package com.mycompany.unittestcases;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.collections.map.MultiValueMap;
import org.junit.jupiter.api.Test;

import com.mycompany.statementprocessor.TransactionParser;

public class TransactionParserTest {
	
	HelperClass helper = new HelperClass();
	@Test
	void testParseCSV() {
		
		TransactionParser parser = new TransactionParser();
		Path path = Paths.get("./resources/testCSVParser.csv");
		MultiValueMap expectedTransactions = helper.generateExpectedOutputForParser();
		MultiValueMap transactions = parser.parseCSV(path);
		boolean isMapsEqual = helper.compareMultiValueMap(transactions, expectedTransactions);
		assertEquals(isMapsEqual, true);
	}

	@Test
	void testParseXML() {
		TransactionParser parser = new TransactionParser();
		Path path = Paths.get("./resources/testXMLParser.xml");
		MultiValueMap expectedTransactions = helper.generateExpectedOutputForParser();
		MultiValueMap transactions = parser.parseXML(path);
		boolean isMapsEqual = helper.compareMultiValueMap(transactions, expectedTransactions);
		assertEquals(isMapsEqual, true);
	}
}
