package com.ca.billdesk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ca.billdesk.common.Constants;
import com.ca.billdesk.model.Merchant;
import com.ca.billdesk.model.MerchantResponse;
import com.ca.billdesk.repository.MerchantRepository;

@Service
public class MerchantServiceImpl implements MerchantService {
	@Autowired
	private MerchantRepository merchantRepository;

	@Override
	public MerchantResponse retrieveAllMerchants() {
		List<Merchant> merchants = merchantRepository.findAll();
		MerchantResponse merchantResponse = new MerchantResponse(merchants);
		merchantResponse.setApplicationCode(Constants.Responses.SUCCESS.getErrorCode());
		merchantResponse.setApplicationResponse(Constants.Responses.SUCCESS.getErrorMessage());
		return merchantResponse;
	}
}
