package com.example.cashbook;

public class Account {

	private String acName ;
	private String acPhone ;
	private String acType;

	public Account(String acName, String acPhone, String acType) {
		super();
		this.acName = acName;
		this.acPhone = acPhone;
		this.acType = acType;

	}

	public String getAcName() {
		return acName;
	}

	public void setAcName(String acName) {
		this.acName = acName;
	}

	public String getAcPhone() {
		return acPhone;
	}

	public void setAcPhone(String acPhone) {
		this.acPhone = acPhone;
	}

	public String getAcType() {
		return acType;
	}

	public void setAcType(String acType) {
		this.acType = acType;
	}
}
