package com.example.appbdcs.service;

import com.example.appbdcs.dto.account.AccountDTO;
import com.example.appbdcs.model.Account;

import java.util.List;
import java.util.Optional;


public interface IAccountService {
    Optional<Account> findAccountByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Account save(Account account);

    Account findByEmail(String email);

    void changePassword(String username, String newPass);

    List<AccountDTO> getAllAccounts();

    void updateAccount(AccountDTO accountDTO);

    void lockAccount(Integer accountId);
}
