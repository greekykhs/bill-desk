package com.ca.billdesk.service;

import com.ca.billdesk.model.TransactionDetails;

public interface PaymentService {
	boolean doTransaction(TransactionDetails transactionDetails);
}
