package ru.itmo.kotiki.dto;


import ru.itmo.kotiki.entity.Role;

public class RoleDto {
    private Long id;
    private Roles role;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.role = role.getRole();
    }
}
