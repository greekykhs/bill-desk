package com.ca.billdesk.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="merchants")
public class Merchant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mid")
	private int mid;
	
	@Column(name = "merchant_type")
	private String merchantType;
	
	@Column(name = "merchant_name")
	private String merchantName;
	
	@OneToMany(mappedBy="merchant")
	private Set<MerchantSubtype> merchantSubtypes;
	
	public String getMerchantType() {
		return merchantType;
	}
	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public Set<MerchantSubtype> getMerchantSubtypes() {
		return merchantSubtypes;
	}
	public void setMerchantSubtypes(Set<MerchantSubtype> merchantSubtypes) {
		this.merchantSubtypes = merchantSubtypes;
	}
	
	public Merchant() {
		super();
	}
	@Override
	public String toString() {
		return "Merchant [mid=" + mid + ", merchantType=" + merchantType + ", merchantName=" + merchantName
				+ ", merchantSubtypes=" + merchantSubtypes + "]";
	}
	
}
