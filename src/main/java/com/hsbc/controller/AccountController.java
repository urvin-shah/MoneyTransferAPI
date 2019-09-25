package com.hsbc.controller;

import com.hsbc.entity.Account;
import com.hsbc.model.Transaction;
import com.hsbc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    Logger logger = Logger.getLogger("AccountController");

    @PostMapping("/{accountId}/createTransaction")
    public Account createTransaction(@PathVariable("accountId") Long accountId, @RequestBody Transaction transaction) {
        logger.info("CreateTransaction Request: "+accountId);
        if(accountId != null && transaction != null) {
            transaction.setAccountId(accountId);
            accountService.createTransaction(transaction);
            return accountService.getAccountDetailsByAccountId(accountId);
        }
        return null;
    }

    @GetMapping("/{accountId}/retrieveBalance")
    public Account retrieveBalance(@PathVariable("accountId") Long accountId) {
        return accountService.getAccountDetailsByAccountId(accountId);
    }

}
