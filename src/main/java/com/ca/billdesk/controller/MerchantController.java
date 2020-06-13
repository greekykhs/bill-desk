package com.ca.billdesk.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ca.billdesk.model.MerchantResponse;
import com.ca.billdesk.service.MerchantService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("This controller contains all the webservices related to Merchant")
public class MerchantController {
	public static final Logger logger = LogManager.getLogger();
	@Autowired
	MerchantService merchantService;
	
	@GetMapping("/getAllMerchants")
	@ApiOperation("This webservice will fetch all the configured merchants")
    public MerchantResponse retrieveAllMerchants() {
		return merchantService.retrieveAllMerchants();
	}
	
	//TODO: web-services to add, update and remove merchants
}
