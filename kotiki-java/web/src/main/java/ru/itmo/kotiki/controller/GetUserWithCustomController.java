package ru.itmo.kotiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itmo.kotiki.conf.AuthenticationFacade;

import java.util.stream.Collectors;

@Controller
public class GetUserWithCustomController {
    @Autowired
    private AuthenticationFacade authenticationFacade;

    @GetMapping(value = "/username")
    @ResponseBody
    public String currentUserNameSimple() {
        Authentication authentication = authenticationFacade.getAuthentication();
        String sOut = "";
        if (authentication != null) {
            sOut = authentication.getAuthorities().stream().map(a -> a.getAuthority())
                    .collect(Collectors.joining(",", "roles={", "}"));
        }
        return sOut + ":" + authentication.getName() + "/" + authentication.getCredentials();
    }
}
