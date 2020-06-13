package com.ca.billdesk.model;

import java.util.List;

public class ReportResponse  extends BillDeskResponse{
	List<TransactionDetails> transactionRequests;

	public List<TransactionDetails> getTransactionRequests() {
		return transactionRequests;
	}

	public void setTransactionRequests(List<TransactionDetails> transactionRequests) {
		this.transactionRequests = transactionRequests;
	}
	
}
