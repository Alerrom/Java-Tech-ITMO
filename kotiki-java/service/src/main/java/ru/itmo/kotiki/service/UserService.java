package ru.itmo.kotiki.service;

import ru.itmo.kotiki.service.dto.AuthorizedUser;

public interface UserService {
    void createUser(AuthorizedUser user);
}
