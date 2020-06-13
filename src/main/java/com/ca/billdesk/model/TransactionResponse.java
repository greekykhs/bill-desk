package com.ca.billdesk.model;

public class TransactionResponse extends BillDeskResponse{
	 private Long txnId;

	public Long getTxnId() {
		return txnId;
	}

	public void setTxnId(Long txnId) {
		this.txnId = txnId;
	}
	
	public TransactionResponse() {
		super();
	}
}
