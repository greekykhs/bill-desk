package com.ca.billdesk.model;

import java.util.ArrayList;
import java.util.List;

public class MerchantResponse extends BillDeskResponse {
	private List<Merchant> merchantTypes=new ArrayList<>();

	public List<Merchant> getMerchantTypes() {
		return merchantTypes;
	}

	public void setMerchantTypes(List<Merchant> merchantTypes) {
		this.merchantTypes = merchantTypes;
	}
	public MerchantResponse() {
		super();
	}
	public MerchantResponse(List<Merchant> merchantTypes) {
		super();
		this.merchantTypes = merchantTypes;
	}
}
