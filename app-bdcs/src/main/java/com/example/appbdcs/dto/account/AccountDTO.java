package com.example.appbdcs.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private Integer accountId;
    private String username;
    private String email;
    private Boolean isEnable;
    private Set<String> roles;
}
