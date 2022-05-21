package ru.itmo.kotiki;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.kotiki.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByName(String name);
}
