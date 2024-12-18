package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.account.AccountDTO;
import com.example.appbdcs.model.Account;
import com.example.appbdcs.repository.IAccountRepository;
import com.example.appbdcs.repository.IRoleRepository;
import com.example.appbdcs.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IRoleRepository roleRepository;

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
        accountRepository.changePassword(username, newPass);
    }

    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAllAccounts().stream().map(record -> {
            AccountDTO dto = new AccountDTO();
            dto.setAccountId((Integer) record[0]);
            dto.setUsername((String) record[1]);
            dto.setEmail((String) record[2]);
            dto.setIsEnable((Boolean) record[3]);
            return dto;
        }).collect(Collectors.toList());
    }

    public void updateAccount(AccountDTO accountDTO) {
        int rowsAffected = accountRepository.updateAccount(
                accountDTO.getAccountId(),
                accountDTO.getUsername(),
                accountDTO.getEmail(),
                accountDTO.getIsEnable()
        );

        if (rowsAffected == 0) {
            throw new RuntimeException("Update failed: Account not found or no changes made.");
        }
    }

    public void lockAccount(Integer accountId) {
        int rowsAffected = accountRepository.lockAccount(accountId);
        if (rowsAffected == 0) {
            throw new RuntimeException("Account not found or already locked.");
        }
    }
}
