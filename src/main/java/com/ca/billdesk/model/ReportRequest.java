package com.ca.billdesk.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ReportRequest {
	@Temporal(TemporalType.TIMESTAMP)
	private Date txnFromDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date txnToDate;
	public Date getTxnFromDate() {
		return txnFromDate;
	}
	public void setTxnFromDate(Date txnFromDate) {
		this.txnFromDate = txnFromDate;
	}
	public Date getTxnToDate() {
		return txnToDate;
	}
	public void setTxnToDate(Date txnToDate) {
		this.txnToDate = txnToDate;
	}
	
	public ReportRequest() {
		super();
	}
}
