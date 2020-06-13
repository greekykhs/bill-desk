package com.ca.billdesk.service;

import com.ca.billdesk.model.TransactionRequest;
import com.ca.billdesk.model.TransactionResponse;

public interface TransactionService {
	TransactionResponse doTransaction(TransactionRequest transactionRequest, String jwt);
}
