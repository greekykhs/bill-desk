package com.ca.billdesk.model;

public class TransactionRequest {
	private String mobileNo;
	private String merchantKey;
	private String amount;
	private String isPartial;
	private String txnMode;
	private String txnRemark;
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getMerchantKey() {
		return merchantKey;
	}
	public void setMerchantKey(String merchantKey) {
		this.merchantKey = merchantKey;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getIsPartial() {
		return isPartial;
	}
	public void setIsPartial(String isPartial) {
		this.isPartial = isPartial;
	}
	public String getTxnMode() {
		return txnMode;
	}
	public void setTxnMode(String txnMode) {
		this.txnMode = txnMode;
	}
	public String getTxnRemark() {
		return txnRemark;
	}
	public void setTxnRemark(String txnRemark) {
		this.txnRemark = txnRemark;
	}
	@Override
	public String toString() {
		return "TransactionRequest [mobileNo=" + mobileNo + ", merchantKey=" + merchantKey + ", amount=" + amount
				+ ", isPartial=" + isPartial + ", txnMode=" + txnMode + ", txnRemark=" + txnRemark + "]";
	}	
}