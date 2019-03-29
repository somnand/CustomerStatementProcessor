package com.mycompany.unittestcases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.collections.map.MultiValueMap;
import org.junit.jupiter.api.Test;

import com.mycompany.statementprocessor.Transaction;
import com.mycompany.statementprocessor.TransactionValidator;


public class TransactionValidatorTest {

	HelperClass helper = new HelperClass();
	@Test
	void testValidate() {
		TransactionValidator validator = new TransactionValidator();
		MultiValueMap inputTransactions =  helper.generateInputForValidator();
		validator.validate(inputTransactions);
		MultiValueMap expectedTransactions = helper.generateExpectedOutputForValidator();
		boolean isMapsEqual = helper.compareMultiValueMap(inputTransactions, expectedTransactions);
		assertEquals(isMapsEqual, true);
	}
	
	@Test
	void testValidateReferenceNumber() {
		TransactionValidator validator = new TransactionValidator();
		MultiValueMap inputTransactions =  new MultiValueMap();
		inputTransactions.put(163590, new Transaction(163590,"NL27SNSB0917829871",105.11,"+29.87","Tickets from Rik Bakker",134.98));
		inputTransactions.put(163590, new Transaction(163590,"NL43AEGO0773393871",28.19,"+3.22","Flowers for Jan Theuﬂ",31.41));
		validator.validate(inputTransactions);
		MultiValueMap expectedTransactions = new MultiValueMap();
		expectedTransactions.put(163590, new Transaction(163590,"NL27SNSB0917829871",105.11,"+29.87","Tickets from Rik Bakker",134.98,true));
		expectedTransactions.put(163590, new Transaction(163590,"NL43AEGO0773393871",28.19,"+3.22","Flowers for Jan Theuﬂ",31.41,true));
		boolean isMapsEqual = helper.compareMultiValueMap(inputTransactions, expectedTransactions);
		assertEquals(isMapsEqual, true);
	}
		
	@Test
	void testValidateEndBalance() {
		TransactionValidator validator = new TransactionValidator();
		MultiValueMap inputTransactions= new MultiValueMap();
		inputTransactions.put(163590, new Transaction(163590,"NL27SNSB0917829871",5.11,"-9.87","Tickets from Rik Bakker",-4.76));
		validator.validate(inputTransactions);
		MultiValueMap expectedTransactions = new MultiValueMap();
		expectedTransactions.put(163590, new Transaction(163590,"NL27SNSB0917829871",5.11,"-9.87","Tickets from Rik Bakker",-4.76, true));
		boolean isMapsEqual = helper.compareMultiValueMap(inputTransactions, expectedTransactions);
		assertEquals(isMapsEqual, true);
	}
}
