package ru.itmo.kotiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dao.UserDao;
import ru.itmo.kotiki.entity.User;
import ru.itmo.kotiki.service.dto.MyUserDetails;

@Service
public class UserDetailServiceImpl implements UserDetailService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userDao.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }
}
