package com.hsbc.service;

import com.hsbc.entity.Account;
import com.hsbc.model.Transaction;
import com.hsbc.model.TransactionType;
import com.hsbc.repository.AccountRepository;
import com.hsbc.repository.AccountTransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @Mock
    AccountTransactionRepository accountTransactionRepository;

    @InjectMocks
    AccountService accountService;

    @Test
    public void addAccountTest() {
        Account account = accountService.addAccount(1l);
        verify(accountRepository,times(1)).save(any());
    }

    @Test
    public void addAccountWithNullCustId() {
        Account account = accountService.addAccount(null);
        verify(accountRepository,times(0)).save(any());
        assertNull(account);
    }

    @Test(expected = NullPointerException.class)
    public void createTransactionWithNullTransaction() {
        accountService.createTransaction(null);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void createTransactionWithNullAccountId() {
        Transaction transaction = new Transaction();
        transaction.setAccountId(null);
        accountService.createTransaction(transaction);
    }

    @Test
    public void createTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAccountId(1l);
        transaction.setCustId(1l);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setTxnAmount(1000d);

        Account account = new Account();
        account.setCustId(1l);
        account.setAccountId(1l);
        account.setBalance(0.0d);

        Optional<Account> optAccount = Optional.of(account);

        when(accountRepository.findById(1l)).thenReturn(optAccount);
        boolean returnVal = accountService.createTransaction(transaction);
        verify(accountTransactionRepository,times(1)).save(any());
        verify(accountRepository,times(1)).save(any());
        assertTrue(returnVal);
    }

    @Test
    public void getAccountDetailsByAccountIdTestWithNullAccountId() {
        Account account = accountService.getAccountDetailsByAccountId(null);
        assertNull(account);
    }

    /*@Test
    public void getAccountDetailsByAccountIdTest() {
        when(accountRepository.findById(anyLong())).thenReturn(any());
        when(accountRepository.findById(anyLong()).get()).thenReturn(any());
        Account account = accountService.getAccountDetailsByAccountId(1l);
        verify(accountRepository,times(1)).findById(1l);
    }*/
}
