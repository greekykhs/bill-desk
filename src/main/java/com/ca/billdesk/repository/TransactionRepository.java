package com.ca.billdesk.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ca.billdesk.model.TransactionDetails;

@Repository("transactionRepository")
public interface TransactionRepository extends JpaRepository<TransactionDetails, Long> {
	//retrieves the transactions done on a given date
	List<TransactionDetails> findAllByTxnDate(Date txnDate);
	
	//retrieves the transactions between two given dates
	@Query(value = "from TransactionDetails td where txnDate BETWEEN :startDate AND :endDate")
    List<TransactionDetails> findAllByTxnDateBetween(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
 
    //retrieves the transactions before given date
    @Query("select td from TransactionDetails td where td.txnDate <= :txnDate")
    List<TransactionDetails> findAllWithTxnDateBefore(@Param("txnDate") Date txnDate);
    
    //findAllByTxnMode will fetch transactions based on the modes e.g credit card, debit card, net banking, e-wallet
    List<TransactionDetails> findAllByTxnMode(String txnMode);
    
    @Modifying
    @Query("UPDATE TransactionDetails td SET td.txnStatus = :txnStatus WHERE td.txnId = :txnId")
    int updateTxnStatus(@Param("txnId") Long txnId, @Param("txnStatus") String txnStatus);
}