package ru.itmo.kotiki.service.dto;

import ru.itmo.kotiki.entity.Role;
import ru.itmo.kotiki.entity.Roles;

public class RoleDto {
    private Long id;
    private Roles role;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.role = role.getRole();
    }
}
