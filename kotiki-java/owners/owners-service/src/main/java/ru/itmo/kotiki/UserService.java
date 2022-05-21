package ru.itmo.kotiki;


import ru.itmo.kotiki.dto.AuthorizedUser;

public interface UserService {
    void createUser(AuthorizedUser user);
}
