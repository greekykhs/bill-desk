package com.ca.billdesk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="merchant_details")
public class MerchantSubtype {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "merchant_key")
	private String merchantKey;
		
	@Column(name = "merchant_type")
	private String merchantType;
	
	@Column(name = "merchant_subtype")
	private String merchantSubType;
	
	@ManyToOne
	private Merchant merchant;
	
	public String getMerchantKey() {
		return merchantKey;
	}
	public void setMerchantKey(String merchantKey) {
		this.merchantKey = merchantKey;
	}
	public String getMerchantType() {
		return merchantType;
	}
	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}
	public String getMerchantSubType() {
		return merchantSubType;
	}
	public void setMerchantSubType(String merchantSubType) {
		this.merchantSubType = merchantSubType;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public MerchantSubtype() {
		super();
	}
}
