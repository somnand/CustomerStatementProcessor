package com.mycompany.statementprocessor;

import java.util.Scanner;

public class StatementProcessor {

	public static void main(String[] args) {
		
		String filepath = null;
		
	
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

	}

}
