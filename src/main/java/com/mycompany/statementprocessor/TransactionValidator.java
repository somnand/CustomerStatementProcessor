package com.mycompany.statementprocessor;

import java.util.Collection;
import java.util.Set;

import org.apache.commons.collections.map.MultiValueMap;

public class TransactionValidator {

	public void validate(MultiValueMap transactions) {

		validateReferenceNumber(transactions);
		validateEndBalance(transactions);
	}

	private void validateEndBalance(MultiValueMap transactions) {

		Set<Integer> keySet = transactions.keySet();

		for (int key :keySet) {

			Collection<Transaction> valueCollection = (Collection) transactions.get(key);

			if(valueCollection.size()>1) {
				for(Transaction temp: valueCollection){
					temp.setFailed(true);
				}
			}
		}

	}

	private void validateReferenceNumber(MultiValueMap transactions) {

		Set<Integer> keySet = transactions.keySet();

		for (int key :keySet) {

			Collection<Transaction> valueCollection = (Collection) transactions.get(key);

			if(valueCollection.size() == 1) {
				for(Transaction temp : valueCollection) {
					if(temp.getEndBalance()<0)
						temp.setFailed(true);
				}
			}
		}
	}

}
