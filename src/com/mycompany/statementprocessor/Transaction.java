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


}
