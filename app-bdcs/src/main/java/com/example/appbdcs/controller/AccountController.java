package com.example.appbdcs.controller;

import com.example.appbdcs.dto.account.AccountDTO;
import com.example.appbdcs.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @GetMapping("/all")
    public List<AccountDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PutMapping("/update")
    public void updateAccount(@RequestBody AccountDTO accountDTO) {
        accountService.updateAccount(accountDTO);
    }

    // Endpoint to lock an account
    @PutMapping("/lock/{id}")
    public void lockAccount(@PathVariable Integer id) {
        accountService.lockAccount(id);
    }


}

