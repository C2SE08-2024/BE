package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.Account;
import com.example.appbdcs.repository.IAccountRepository;
import com.example.appbdcs.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public Optional<Account> findAccountByUsername(String username) {
        return accountRepository.findAccountByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return accountRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public void changePassword(String username, String newPass) {
        accountRepository.changePassword(username,newPass);
    }
}
