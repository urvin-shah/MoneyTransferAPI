package com.hsbc.controller;


import com.hsbc.MoneyTransferApiApplication;
import com.hsbc.model.Transaction;
import com.hsbc.model.TransactionType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MoneyTransferApiApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Before
    public void init() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    @Test
    public void retrieveBalanceTest() {
        String url = "http://localhost:" + port + "/account/5";
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(url,
                HttpMethod.GET, entity, String.class);
        String expectedString = "{\"accountId\":5,\"custId\":2,\"balance\":0.0}";
        System.out.println("Out put string:"+response.getBody());
        assertEquals(expectedString,response.getBody());
        assertEquals(200,response.getStatusCodeValue());
    }

    @Test
    public void retrieveBalanceTestWithException() {
        String url = "http://localhost:" + port + "/account/1";
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(url,
                HttpMethod.GET, entity, String.class);
        String expectedString = "{\"exception\":\"No value present\",\"errorCode\":\"BAD_REQUEST\",\"date\":\"2019-09-27\"}";
        assertEquals(expectedString,response.getBody());
    }

    @Test
    public void createTransactionTest() {
        String url = "http://localhost:" + port + "/account/createTransaction";

        Transaction transaction = new Transaction();
        transaction.setCustId(1l);
        transaction.setAccountId(4L);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setTxnAmount(1000d);

        HttpEntity entity = new HttpEntity<Transaction>(transaction, headers);

        ResponseEntity<String> response = restTemplate.exchange(url,
                HttpMethod.POST, entity, String.class);
        String expectedString = "{\"accountId\":4,\"custId\":1,\"balance\":1000.0}";
        System.out.println("Out put string:"+response.getBody());
        assertEquals(expectedString,response.getBody());

        assertEquals(200,response.getStatusCodeValue());
    }



}