package com.workintech.s18d4.controller;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;
    private CustomerService customerService;

    @Autowired
    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

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
        Account account = accountService.find(id);
        if(account!=null){
            return accountService.delete(id);
        }
        return null;
    }
    @PutMapping("/{id}")
    public Account update(@PathVariable Long id, @RequestBody Account account) {
        Account foundAccount = accountService.find(id);

        if (foundAccount != null) {
            if (account.getCustomer() != null) {
                Customer customer = customerService.find(account.getCustomer().getId());
                if (customer != null) {
                    account.setCustomer(customer);
                }
            } else {
                account.setCustomer(foundAccount.getCustomer());
            }

            account.setId(id);
            return accountService.save(account);
        }
        return null;
    }
}
