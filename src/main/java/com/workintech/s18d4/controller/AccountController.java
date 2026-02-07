package com.workintech.s18d4.controller;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AccountController {
    private AccountService accountService;
    private CustomerService customerService;


    @GetMapping
    public List<Account> findAll(){
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account find(@PathVariable Long id){
        return accountService.find(id);
    }

    @PostMapping("/{customerId}")
    public Account save(@PathVariable Long customerId, @RequestBody Account account){
        Customer customer = customerService.find(customerId);
        if(customer!=null){
            account.setCustomer(customer);
            return accountService.save(account);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Account delete(@PathVariable Long id){
        return accountService.delete(id);
    }
}
