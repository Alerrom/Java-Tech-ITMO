package ru.itmo.kotiki.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itmo.kotiki.service.UserService;
import ru.itmo.kotiki.service.dto.AuthorizedUser;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> getByName(@RequestBody AuthorizedUser user) {
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }
}
