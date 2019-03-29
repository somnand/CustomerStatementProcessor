package com.mycompany.statementprocessor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.opencsv.bean.CsvBindByName;

@XmlRootElement(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction {

	@CsvBindByName(column = "Reference", required = true)
	@XmlAttribute(name="reference")
	private int transactionNo;

	@CsvBindByName(column = "Account Number", required = true)
	@XmlElement(name="accountNumber")
	private String accountNo;

	@CsvBindByName(column = "Start Balance", required = true)
	@XmlElement(name="startBalance")
	private double startBalance;

	@CsvBindByName(column = "Mutation", required = true)
	@XmlElement(name="mutation")
	private String mutation;
	
	@CsvBindByName(column = "Description", required = true)
	@XmlElement(name="description")
	private String Description;
	
	@CsvBindByName(column = "End Balance", required = true)
	@XmlElement(name="endBalance")
	private double endBalance;
	
	private boolean failed = false;

	public Transaction(int transactionNo, String accountNo, double startBalance, String mutation, String description,
			double endBalance, boolean failed) {
		super();
		this.transactionNo = transactionNo;
		this.accountNo = accountNo;
		this.startBalance = startBalance;
		this.mutation = mutation;
		Description = description;
		this.endBalance = endBalance;
		this.failed = failed;
	}
	
	public Transaction(int transactionNo, String accountNo, double startBalance, String mutation, String description,
			double endBalance) {
		super();
		this.transactionNo = transactionNo;
		this.accountNo = accountNo;
		this.startBalance = startBalance;
		this.mutation = mutation;
		Description = description;
		this.endBalance = endBalance;
	}
	
	public Transaction() {
		super();
	}

	@Override
	public String toString() {
		return "Transaction [transactionNo=" + transactionNo + ", accountNo=" + accountNo + ", startBalance="
				+ startBalance + ", mutation=" + mutation + ", Description=" + Description + ", endBalance="
				+ endBalance + ", failed=" + failed + "]";
	}

	public int getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(int transactionNo) {
		this.transactionNo = transactionNo;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public double getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(double startBalance) {
		this.startBalance = startBalance;
	}

	public String getMutation() {
		return mutation;
	}

	public void setMutation(String mutation) {
		this.mutation = mutation;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public double getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(double endBalance) {
		this.endBalance = endBalance;
	}

	public boolean isFailed() {
		return failed;
	}

	public void setFailed(boolean failed) {
		this.failed = failed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Description == null) ? 0 : Description.hashCode());
		result = prime * result + ((accountNo == null) ? 0 : accountNo.hashCode());
		long temp;
		temp = Double.doubleToLongBits(endBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (failed ? 1231 : 1237);
		result = prime * result + ((mutation == null) ? 0 : mutation.hashCode());
		temp = Double.doubleToLongBits(startBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + transactionNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (Description == null) {
			if (other.Description != null)
				return false;
		} else if (!Description.equals(other.Description))
			return false;
		if (accountNo == null) {
			if (other.accountNo != null)
				return false;
		} else if (!accountNo.equals(other.accountNo))
			return false;
		if (Double.doubleToLongBits(endBalance) != Double.doubleToLongBits(other.endBalance))
			return false;
		if (failed != other.failed)
			return false;
		if (mutation == null) {
			if (other.mutation != null)
				return false;
		} else if (!mutation.equals(other.mutation))
			return false;
		if (Double.doubleToLongBits(startBalance) != Double.doubleToLongBits(other.startBalance))
			return false;
		if (transactionNo != other.transactionNo)
			return false;
		return true;
	}


}
