package com.example.cashbook;

public class Transaction {
	private String trName ;
	private String trDate;
	private String trAmount ;
	private String trType;
	public Transaction(String trName, String trDate, String trAmount,
			String trType) {
		super();
		this.trName = trName;
		this.trDate = trDate;
		this.trAmount = trAmount;
		this.trType = trType;
	}
	public String getTrName() {
		return trName;
	}
	public void setTrName(String trName) {
		this.trName = trName;
	}
	public String getTrDate() {
		return trDate;
	}
	public void setTrDate(String trDate) {
		this.trDate = trDate;
	}
	public String getTrAmount() {
		return trAmount;
	}
	public void setTrAmount(String trAmount) {
		this.trAmount = trAmount;
	}
	public String getTrType() {
		return trType;
	}
	public void setTrType(String trType) {
		this.trType = trType;
	}
	
	

}
