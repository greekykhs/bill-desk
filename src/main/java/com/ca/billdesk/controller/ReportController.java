package com.ca.billdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ca.billdesk.common.Constants;
import com.ca.billdesk.model.ReportRequest;
import com.ca.billdesk.model.ReportResponse;
import com.ca.billdesk.service.ReportService;
import com.sun.istack.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@Api("This controller contains all the webservices which will generate the reports.")
@RequestMapping("/reports")
public class ReportController {
	@Autowired
	ReportService reportService;
	
	@PostMapping("/generateReport")
	@ApiOperation("generateReport webservice will fetch the details of transactions done netween specified dates.")
	public ReportResponse generateReport(@NotNull @RequestBody ReportRequest reportRequest,
			@RequestHeader(Constants.REQUEST_HEADER_TOKEN) String token) {	
		return reportService.generateReport(reportRequest, token);
	}
}
