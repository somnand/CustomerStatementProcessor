package com.mycompany.unittestcases;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.collections.map.MultiValueMap;

import com.mycompany.statementprocessor.Transaction;

public class HelperClass {
	public MultiValueMap generateInputForValidator() {
		return generateExpectedOutputForParser();
	}
	
	public boolean compareMultiValueMap(MultiValueMap transactions, MultiValueMap expectedTransactions) {
		
		Set<Integer> keySet = transactions.keySet();
		Set<Integer> expectedkeySet = expectedTransactions.keySet();
		if(keySet.size()!=keySet.size())
			return false;
		for (int key :keySet) {
			Collection<Transaction> resultCollection = (Collection<Transaction>) transactions.get(key);
			Collection<Transaction> expectedCollection = (Collection<Transaction>)expectedTransactions.get(key);
			if(resultCollection.size() != expectedCollection.size())
				return false;
			Iterator<Transaction> iterator = expectedCollection.iterator();
			for(Transaction temp: resultCollection){
					if(!temp.equals(iterator.next()))
					return false;
				}
		}
		return true;
	}

	public MultiValueMap generateExpectedOutputForParser() {
		
		MultiValueMap transactions = new MultiValueMap();
		transactions.put(163590, new Transaction(163590,"NL27SNSB0917829871",105.11,"+29.87","Tickets from Rik Bakker",134.98));
		transactions.put(109762, new Transaction(109762,"NL93ABNA0585619023",47.45,"+17.82","Flowers from Rik de Vries",65.27));
		transactions.put(196213, new Transaction(196213,"NL32RABO0195610843",30.36,"-35.1","Subscription from Rik de Vries",-4.74));
		transactions.put(163590, new Transaction(163590,"NL43AEGO0773393871",28.19,"+3.22","Flowers for Jan Theuﬂ",31.41));
		return transactions;
	}

	public MultiValueMap generateExpectedOutputForValidator() {
		
		MultiValueMap transactions = generateExpectedOutputForParser();
		Collection<Transaction> collection = (Collection) transactions.get(163590);
		for(Transaction temp: collection){
			temp.setFailed(true);
		}
		collection = (Collection) transactions.get(196213);
		for(Transaction temp: collection){
			temp.setFailed(true);
		}
		return transactions;
	}

	public MultiValueMap generateInputForReportGenerator() {
		
		return generateExpectedOutputForValidator();
	}

}
