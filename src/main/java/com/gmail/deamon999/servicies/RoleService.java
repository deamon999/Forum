package com.gmail.deamon999.servicies;

import com.gmail.deamon999.entities.Role;
import com.gmail.deamon999.reposotories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Transactional
    public Role findByName(String name) {
        return roleRepository.findByRole(name);
    }
}
