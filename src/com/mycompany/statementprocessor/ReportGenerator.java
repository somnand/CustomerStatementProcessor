package com.mycompany.statementprocessor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.apache.commons.collections.map.MultiValueMap;

public class ReportGenerator {
	
	public String generateReport(MultiValueMap transacitons) {
		String lineofText= null;
		boolean foundRecord = false;
		String outputFileName = "failedTransactionReport"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".csv";
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));) {
			
			writer.write("Reference,Description\n");
			Set<Integer> keySet = transacitons.keySet();
			for (int key :keySet) {
				Collection<Transaction> valueCollection = (Collection) transacitons.get(key);
				for(Transaction temp: valueCollection){
						if(temp.isFailed()) {
							if(!foundRecord)
								foundRecord = true;
							lineofText = temp.getTransactionNo()+","+temp.getDescription()+"\n";
							writer.write(lineofText);
						} 
					}
			}
			if(!foundRecord)
				writer.write("No failed transactions to report\n");			
		} catch (IOException e) {
			
			System.out.println("Error Occured !!!!!!!Check the log file for error.");
		}
		return outputFileName;
	}

}
