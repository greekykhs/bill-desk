package com.ca.billdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ca.billdesk.model.Merchant;

@Repository("merchantRepository")
public interface MerchantRepository extends JpaRepository<Merchant, Long> { 
	Merchant findByMerchantType(String merchantType);
}