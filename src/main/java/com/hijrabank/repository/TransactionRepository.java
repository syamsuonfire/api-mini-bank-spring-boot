package com.hijrabank.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import com.hijrabank.model.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	// transactionType=
	// 1: CREDIT
	// 2: DEBIT

	// History Transaction
	// 1. Using Derived Query
	List<Transaction> findTransactionByAccountId(Long idAccount);
	
	// 2. Using JPA Query
	// @Query("SELECT t FROM Transaction t WHERE t.account.id = :idAccount")
	// List<Transaction> findTransactionByAccountId(@PathParam("idAccount") Long idAccount);


	// Check Total Day Debit
	// 1. Using JPA Query
	@Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.account.id =:idAccount AND t.transactionType=2 AND CAST(t.createdAt AS date)=CURRENT_DATE")
	BigDecimal totalDayDebit(@PathParam("idAccount") Long idAccount);
	
	// 2. Using Native Query
	// @Query(value = "SELECT SUM(amount) FROM transactions WHERE account_id=:idAccount AND transaction_type=2 AND CAST(created_at AS date)=CURRENT_DATE", nativeQuery = true)
	// BigDecimal totalDayDebit(@Param("idAccount") Long idAccount);



	// History Transaction By Date
	// 1. Using JPA Query
	@Query("SELECT t FROM Transaction t WHERE t.account.id=:idAccount AND CAST(t.createdAt AS date) "
	+ "BETWEEN CAST(:fromDate AS date) AND CAST(:toDate AS date)")
	List<Transaction> findByAccountIdWithCreationDateBetween(@PathParam("idAccount") Long idAccount,
	@PathParam("fromDate") Date fromDate, @PathParam("toDate") Date toDate);

	// 2. Using Native Query
	// @Query(value = "SELECT * FROM transactions WHERE account_id=:idAccount AND CAST(created_at AS date) "
	// + "BETWEEN CAST(:fromDate AS date) AND CAST(:toDate AS date)", nativeQuery = true)
	// List<Transaction> findByAccountIdWithCreationDateBetween(@Param("idAccount") Long idAccount,
	// 		@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

}
