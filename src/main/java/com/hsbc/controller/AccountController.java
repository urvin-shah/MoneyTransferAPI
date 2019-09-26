package com.hsbc.controller;

import com.hsbc.entity.Account;
import com.hsbc.exception.ExceptionMessage;
import com.hsbc.model.Transaction;
import com.hsbc.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@RestController
@Api(value="/account",description="Account details and transaction",produces ="application/json")
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    Logger logger = Logger.getLogger("AccountController");

    @ApiOperation(value="Perform Deposit/Withdraw Transaction",response=Account.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="Account Details Retrieved",response=Account.class),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code=404,message="Account not found"),
            @ApiResponse(code=400,message="Bad Request")
    })
    @PostMapping("/createTransaction")
    public Account createTransaction(@RequestBody Transaction transaction) {
        logger.info("CreateTransaction Request: "+transaction.getAccountId());
        if(transaction.getAccountId() != null && transaction != null) {
            transaction.setAccountId(transaction.getAccountId());
            accountService.createTransaction(transaction);
            return accountService.getAccountDetailsByAccountId(transaction.getAccountId());
        }
        return null;
    }

    @ApiOperation(value="Get Account Balance",response=Account.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="Account Details Retrieved",response=Account.class),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code=404,message="Account not found"),
            @ApiResponse(code=400,message="Bad Request")
    })
    @GetMapping("/{accountId}")
    public Account retrieveBalance(@PathVariable("accountId") Long accountId) {
        return accountService.getAccountDetailsByAccountId(accountId);
    }

}
