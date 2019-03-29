package com.mycompany.statementprocessor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.collections.map.MultiValueMap;
import org.apache.log4j.Logger;

import com.opencsv.bean.CsvToBeanBuilder;

public class TransactionParser {

	static Logger logger = Logger.getLogger(TransactionParser.class);

	public MultiValueMap parseCSV(Path path) {
		MultiValueMap transactions = null;
		List<Transaction> list;

		try {
			processInputFile(path);
			CsvToBeanBuilder<Transaction> beanBuilder = new CsvToBeanBuilder<>(new InputStreamReader(new FileInputStream(path.toString())));
			beanBuilder.withType(Transaction.class);
			// build methods returns a list of Beans
			list =(List<Transaction>) beanBuilder.build().parse();
			if(!(list.size() == 0))
				transactions = new MultiValueMap();
			for(Transaction temp : list){
				transactions.put(temp.getTransactionNo(), temp);
			}

		} catch (FileNotFoundException e) {

			logger.error("Exceptions happen!", e);
			System.out.println("Error Occured !!!!!!!Check the log file for error.");
		}

		return transactions;
	}

	public MultiValueMap parseXML(Path path) {
		MultiValueMap transactions = new MultiValueMap();
		File xmlFile = new File(path.toString());
		Transactions list;

		JAXBContext jaxbContext;
		try
		{
			jaxbContext = JAXBContext.newInstance(Transactions.class);             

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			list =(Transactions) jaxbUnmarshaller.unmarshal(xmlFile);

			if(list.getTransactions().size() == 0)
				return null; 

			for(Transaction temp : list.getTransactions()){
				transactions.put(temp.getTransactionNo(), temp);
			}

		}
		catch (JAXBException e)
		{

			logger.error("Exceptions happen!", e);
			System.out.println("Error Occured !!!!!!!Check the log file for error.");
		}
		return transactions;
	}

	private void processInputFile(Path path) {

		String outputFileName = path.toString()+".tmp";

		try(BufferedReader reader = new BufferedReader(new FileReader(path.toString()));
				BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));) {

			String lineOfText = reader.readLine();
			while(lineOfText != null)
			{
				if (!(lineOfText.isEmpty()))
				{
					writer.write(lineOfText+"\n");
				}

				lineOfText = reader.readLine();
			} 

		} catch (IOException e) {

			logger.error("Exceptions happen!", e);
			System.out.println("Error Occured !!!!!!!Check the log file for error.");
		}

		File inputFile = new File(path.toString());
		File outputFile = new File(outputFileName);

		inputFile.delete();
		outputFile.renameTo(inputFile);
	}

}
