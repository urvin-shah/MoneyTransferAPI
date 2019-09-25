package com.hsbc.command;

import com.hsbc.entity.Customer;
import com.hsbc.service.AccountService;
import com.hsbc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class AccountCreationCommandRunner implements CommandLineRunner {

    @Autowired
    CustomerService customerService;

    @Autowired
    AccountService accountService;

    Logger logger = Logger.getLogger("AccountCreationCommandRunner");

    @Override
    public void run(String... args) throws Exception {
        // Create Customers.
        Customer cust1 = customerService.addCustomer("Urvin");
        Customer cust2 = customerService.addCustomer("Sweta");
        Customer cust3 = customerService.addCustomer("Riyal");

        logger.info("Customers created successfully");
        // Create Accounts of the customers.

        accountService.addAccount(cust1.getCustId());
        accountService.addAccount(cust2.getCustId());
        accountService.addAccount(cust3.getCustId());
        logger.info("Customers' accounts created successfully");
    }
}
