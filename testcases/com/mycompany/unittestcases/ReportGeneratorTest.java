package com.mycompany.unittestcases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.collections.map.MultiValueMap;
import org.junit.jupiter.api.Test;

import com.mycompany.statementprocessor.ReportGenerator;
import com.mycompany.statementprocessor.Transaction;


public class ReportGeneratorTest {
	
	HelperClass helper = new HelperClass();
	@Test
	void testGenerateReport() {
		ReportGenerator reportGenerator = new ReportGenerator();
		MultiValueMap inputTransactions = helper.generateInputForReportGenerator();
		String outputFileName = reportGenerator.generateReport(inputTransactions);
		BufferedReader outputWriter; 
		BufferedReader expectedOutputWriter;
		try {
			outputWriter = new BufferedReader(new FileReader(outputFileName));
			expectedOutputWriter = new BufferedReader(new FileReader("./resources/expectedOutput.csv"));
			assertReaders(expectedOutputWriter, outputWriter);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testAllPassingGenerateReport() {
		ReportGenerator reportGenerator = new ReportGenerator();
		MultiValueMap inputTransactions= new MultiValueMap();
		inputTransactions.put(163590, new Transaction(163590,"NL27SNSB0917829871",105.11,"+29.87","Tickets from Rik Bakker",134.98));
		inputTransactions.put(109762, new Transaction(109762,"NL93ABNA0585619023",47.45,"+17.82","Flowers from Rik de Vries",65.27));
		String outputFileName = reportGenerator.generateReport(inputTransactions);
		BufferedReader outputWriter;
		BufferedReader expectedOutputWriter;
		try {
			outputWriter = new BufferedReader(new FileReader(outputFileName));
			expectedOutputWriter = new BufferedReader(new FileReader("./resources/expectedAllPassingOutput.csv"));
			assertReaders(expectedOutputWriter, outputWriter);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void assertReaders(BufferedReader expected,
	          BufferedReader actual) throws IOException {
	    String expectedLine, actualLine = null;
	    while (((expectedLine = expected.readLine()) != null) && ((actualLine = actual.readLine()) != null)) {
	        assertEquals(expectedLine, actualLine);
	    }

	    if(expectedLine == null ) {
	    	if(actualLine == null)	//Case 1 & 1
	    		assertTrue(true);
	    	else if(actual.readLine() == null)  //case 1 & 1
	    		assertTrue(true);
	    	else assertTrue(false,"Actual file has more lines"); // 1 & 0
	    }
	    else if(actualLine == null)
	    	assertTrue(false, "Ëxpected file has more lines"); //case 0 & 1
	    
	    // won't reach case 0 & 0 because the loop won't run out
	}

}
