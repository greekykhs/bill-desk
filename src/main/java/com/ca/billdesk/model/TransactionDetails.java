package com.ca.billdesk.model;

import java.util.Date;
import java.util.function.Function;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="transaction_details")
public class TransactionDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "txn_id")
    private Long txnId;
	
	@Column(name = "user_id")
    private String userId;
	
	@Column(name = "mobile_no")
    private String mobileNo;
	
	@Column(name = "merchant_key")
    private String merchantKey;
	
	@Column(name = "amount")
    private String amount;
	
	@Column(name = "is_partial")
    private String isPartial;
	
	@Column(name = "txn_status")
    private String txnStatus;
	
	@Column(name = "txn_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
    private Date txnDate;
	
	@Column(name = "txn_mode")
    private String txnMode;
	
	@Column(name = "txn_remark")
    private String txnRemark;

	public TransactionDetails() {
		super();
	}
	
	public static TransactionDetails getTxnDtlsFromRequest(TransactionRequest transactionRequest) {
		Function<TransactionRequest, TransactionDetails> externalToTransactionDetails 
		= new Function<TransactionRequest, TransactionDetails>() {
			public TransactionDetails apply(TransactionRequest txnRequest) {
				TransactionDetails transactionDetails = new TransactionDetails();
				transactionDetails.setAmount(txnRequest.getAmount());
				transactionDetails.setIsPartial(txnRequest.getIsPartial());
				transactionDetails.setMerchantKey(txnRequest.getMerchantKey());
				transactionDetails.setMobileNo(txnRequest.getMobileNo());
				transactionDetails.setTxnRemark(txnRequest.getTxnRemark());

				return transactionDetails;
			}
		};		
		return externalToTransactionDetails.apply(transactionRequest);
	}
	
	public Long getTxnId() {
		return txnId;
	}

	public void setTxnId(Long txnId) {
		this.txnId = txnId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	public String getTxnStatus() {
		return txnStatus;
	}

	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}

	public Date getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
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
		return "TransactionDetails [txnId=" + txnId + ", userId=" + userId + ", mobileNo=" + mobileNo + ", merchantKey="
				+ merchantKey + ", amount=" + amount + ", isPartial=" + isPartial + ", txnStatus=" + txnStatus
				+ ", txnDate=" + txnDate + ", txnMode=" + txnMode + ", txnRemark=" + txnRemark + "]";
	}
	
	
}