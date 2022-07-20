package ru.itmo.kotiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dao.RoleDao;
import ru.itmo.kotiki.dao.UserDao;
import ru.itmo.kotiki.entity.Owner;
import ru.itmo.kotiki.entity.User;
import ru.itmo.kotiki.service.dto.AuthorizedUser;

@Service
public class UserServiceImpl implements UserService {
    private final OwnerService ownerService;
    private final UserDao userDao;
    private final RoleDao roleDao;

    @Autowired
    public UserServiceImpl(OwnerService ownerService, UserDao userDao, RoleDao roleDao) {
        this.ownerService = ownerService;
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public void createUser(AuthorizedUser user) {
        Owner owner = ownerService.saveOwner(new Owner(user.getName(), user.getDate()));
        userDao.save(new User(user.getName(),
                user.getPassword(),
                user.isFlag(),
                roleDao.findByRole(user.getRole()),
                owner));
    }
}
