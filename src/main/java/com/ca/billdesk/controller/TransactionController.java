package com.ca.billdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ca.billdesk.common.Constants;
import com.ca.billdesk.model.TransactionDetails;
import com.ca.billdesk.model.TransactionRequest;
import com.ca.billdesk.model.TransactionResponse;
import com.ca.billdesk.service.TransactionService;
import com.sun.istack.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/transaction")
@Api("This controller contains all the transaction related webservices.")
public class TransactionController {
	@Autowired
	TransactionService transactionService;
	
	@PostMapping("/doTransaction")
	@ApiOperation("This webservice will do the actual transaction.")
	public TransactionResponse doTransaction(@NotNull @RequestBody TransactionRequest transactionRequest,
			@RequestHeader(value=Constants.REQUEST_HEADER_TOKEN, required=false) String token) {	
		return transactionService.doTransaction(transactionRequest, token);
	}
}
