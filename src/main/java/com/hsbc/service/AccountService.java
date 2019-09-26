package com.hsbc.service;


import com.hsbc.entity.Account;
import com.hsbc.entity.AccountTransaction;
import com.hsbc.model.Transaction;
import com.hsbc.model.TransactionType;
import com.hsbc.repository.AccountRepository;
import com.hsbc.repository.AccountTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountTransactionRepository accountTransactionRepository;

    public Account addAccount(Long custId) {
        if(custId != null) {
            Account account = new Account();
            account.setCustId(custId);
            account.setBalance(0.0);
            return accountRepository.save(account);
        }
        return null;
    }

    @Transactional
    public boolean createTransaction(Transaction transaction) {
        Account account = accountRepository.findById(transaction.getAccountId()).get();
        if(account != null) {
            Double balanceAfterTxn=0.0;
            AccountTransaction accountTransaction = new AccountTransaction();
            accountTransaction.setAccountId(transaction.getAccountId());
            accountTransaction.setTransactionType(transaction.getTransactionType());
            accountTransaction.setBalanceBeforeTxn(account.getBalance());
            accountTransaction.setTxnAmount(transaction.getTxnAmount());
            if (TransactionType.DEPOSIT.equals(transaction.getTransactionType())) {
                balanceAfterTxn = (account.getBalance()+transaction.getTxnAmount());
            } else if(TransactionType.WITHDRAW.equals(transaction.getTransactionType())) {
                balanceAfterTxn = (account.getBalance()-transaction.getTxnAmount());
            }
            accountTransaction.setBalanceAfterTxn(balanceAfterTxn);
            accountTransactionRepository.save(accountTransaction);
            account.setBalance(balanceAfterTxn);
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    public Account getAccountDetailsByAccountId(Long accountId) {
        if(accountId != null) {
            return accountRepository.findById(accountId).get();
        }
        return null;
    }
}
