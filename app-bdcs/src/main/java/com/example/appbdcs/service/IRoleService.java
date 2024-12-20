package com.example.appbdcs.service;

import com.example.appbdcs.model.Role;

public interface IRoleService {
    Role findByName(String roleName);
}
