package com.ca.billdesk.service;

import com.ca.billdesk.model.ReportRequest;
import com.ca.billdesk.model.ReportResponse;

public interface ReportService {
	ReportResponse generateReport(ReportRequest reportRequest, String token);
}
