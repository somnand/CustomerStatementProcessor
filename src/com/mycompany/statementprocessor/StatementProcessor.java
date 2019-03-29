package com.mycompany.statementprocessor;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.commons.collections.map.MultiValueMap;

public class StatementProcessor {

	public static void main(String[] args) {
		
		String filepath = null;
		MultiValueMap transactions = new MultiValueMap();
		TransactionParser parser = new TransactionParser();
	
			if(args.length == 1) {
				filepath = args[0];
			}
			
			else if(args.length == 0) {
				
				System.out.println("Please enter a file name:");
				
				Scanner scanner = new Scanner(System. in);
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

	}

}
