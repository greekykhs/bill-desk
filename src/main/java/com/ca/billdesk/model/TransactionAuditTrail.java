package com.ca.billdesk.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="transaction_audittrail")
public class TransactionAuditTrail {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "at_id")
    private Long atId;
	
	@Column(name = "txn_id")
    private Long txnId;
	
	@Column(name = "txn_status")
    private String txnStatus;
	
	@Column(name = "txn_date")
	@Temporal(TemporalType.TIMESTAMP)
    private Date txnDate;
	
	@Column(name = "txn_remark")
    private String txnRemark;
	
	public TransactionAuditTrail() {
		super();
	}
	
	public TransactionAuditTrail(TransactionDetails transactionRequest) {
		this.setTxnDate(transactionRequest.getTxnDate());
		this.setTxnId(transactionRequest.getTxnId());
		this.setTxnStatus(transactionRequest.getTxnStatus());
		this.setTxnRemark(transactionRequest.getTxnRemark());
	}

	public Long getAtId() {
		return atId;
	}

	public void setAtId(Long atId) {
		this.atId = atId;
	}

	public Long getTxnId() {
		return txnId;
	}

	public void setTxnId(Long txnId) {
		this.txnId = txnId;
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

	public String getTxnRemark() {
		return txnRemark;
	}

	public void setTxnRemark(String txnRemark) {
		this.txnRemark = txnRemark;
	}
	
	
}
