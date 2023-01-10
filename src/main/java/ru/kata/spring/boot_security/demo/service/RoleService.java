package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repo.RoleRepository;

import java.util.List;

public interface RoleService {

    public List<Role> showRoles();

}
