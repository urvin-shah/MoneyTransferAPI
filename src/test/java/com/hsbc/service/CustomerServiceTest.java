package com.hsbc.service;

import com.hsbc.entity.Customer;
import com.hsbc.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;

    @Test
    public void addCustomerTest() {
        String customerName = "Urvin Shah";
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        when(customerRepository.save(any())).thenReturn(customer);
        Customer returnCustomer =  customerService.addCustomer(customerName);
        assertEquals(customerName,returnCustomer.getCustomerName());
    }

    @Test
    public void addCustomerWithNull() {
        String customerName = null;
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        when(customerRepository.save(any())).thenReturn(customer);
        Customer returnCustomer =  customerService.addCustomer(customerName);
        verify(customerRepository,times(1)).save(any());
    }
}
