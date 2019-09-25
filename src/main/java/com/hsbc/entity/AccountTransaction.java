package com.hsbc.entity;

import com.hsbc.model.TransactionType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccountTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;
    private Long accountId;
    private Double balanceBeforeTxn;
    private Double txnAmount;
    private Double balanceAfterTxn;
    private TransactionType transactionType;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Double getBalanceBeforeTxn() {
        return balanceBeforeTxn;
    }

    public void setBalanceBeforeTxn(Double balanceBeforeTxn) {
        this.balanceBeforeTxn = balanceBeforeTxn;
    }

    public Double getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public Double getBalanceAfterTxn() {
        return balanceAfterTxn;
    }

    public void setBalanceAfterTxn(Double balanceAfterTxn) {
        this.balanceAfterTxn = balanceAfterTxn;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
