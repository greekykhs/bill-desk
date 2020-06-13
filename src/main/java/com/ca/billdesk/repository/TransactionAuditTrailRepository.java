package com.ca.billdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ca.billdesk.model.TransactionAuditTrail;

@Repository("transactionAuditTrailRepository")
public interface TransactionAuditTrailRepository extends JpaRepository<TransactionAuditTrail, Long> {
}