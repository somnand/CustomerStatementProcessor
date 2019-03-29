package com.mycompany.statementprocessor;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.commons.collections.map.MultiValueMap;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class StatementProcessor {

	static Logger logger = Logger.getLogger(StatementProcessor.class);

	public static void main(String[] args) {

		String filepath = null;
		MultiValueMap transactions = null;
		TransactionParser parser = new TransactionParser();
		TransactionValidator validator = new TransactionValidator();
		ReportGenerator generator = new ReportGenerator();

		PropertyConfigurator.configure("log4j.properties");
		
		try(Scanner scanner = new Scanner(System. in);) {
			
			if(args.length == 1) {
				filepath = args[0];
			}

			else if(args.length == 0) {

				System.out.println("Please enter a file name:");

				
				filepath = scanner. nextLine();

				if(filepath.trim().isEmpty()) {

					System.out.println("Please Enter a valid file name next time");
					return;
				}
			}

			else {

				System.out.println("The correct usage is \"command\" [filepath]");
				return;
			}

			Path target = Paths.get(filepath);

			if(filepath.endsWith(".csv"))
				transactions = parser.parseCSV(target);
			else if(filepath.endsWith(".xml"))
				transactions = parser.parseXML(target);

			if(transactions == null) {
				System.out.println("There is nothing to process. Please refer error log");
				return;
			}

			validator.validate(transactions);

			String outputFileName = generator.generateReport(transactions);

			if(outputFileName!=null)
				System.out.println("The generated file is: "+outputFileName);
		}
		catch(Exception e) {
			logger.error("Exceptions happen!", e);
			System.out.println("Error Occured !!!!!!!Check the log file for error.");
		}

	}

}
