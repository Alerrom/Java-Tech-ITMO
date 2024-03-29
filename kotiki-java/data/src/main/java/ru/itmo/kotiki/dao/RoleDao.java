package ru.itmo.kotiki.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.kotiki.entity.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
