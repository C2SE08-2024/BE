package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.Role;
import com.example.appbdcs.repository.IRoleRepository;
import com.example.appbdcs.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Role findByName(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
    }
}
