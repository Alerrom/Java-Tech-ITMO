package ru.itmo.kotiki.ampq;

import ru.itmo.kotiki.dto.Roles;

public record RabbitUserInfo(
        String name,
        String password,
        boolean flag,
        Roles role,
        Integer ownerId) {
}
