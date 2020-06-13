package com.ca.billdesk.service;

import org.springframework.stereotype.Service;
import com.ca.billdesk.model.TransactionDetails;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Override
	public boolean doTransaction(TransactionDetails transactionDetails){
		if(Math.random()>10)
			throw new RuntimeException("Payment failed..");
		return true;
	}

}
