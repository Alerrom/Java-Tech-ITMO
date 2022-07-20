package ru.itmo.kotiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.kotiki.service.CatService;
import ru.itmo.kotiki.service.dto.CatDto;
import ru.itmo.kotiki.service.dto.MyUserDetails;

import java.util.List;

@RestController
@RequestMapping("cats")
public class CatController {
    private final CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping
    public List<CatDto> getAll() {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int ownerId = myUserDetails.getUser().getOwner().getId();
        List<CatDto> res = catService.findAllByOwnerId(ownerId);
        return res;
    }

    @GetMapping(value = "/{id}")
    public CatDto getById(@RequestParam("id") Integer id) {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int ownerId = myUserDetails.getUser().getOwner().getId();
        CatDto cat = catService.findByIdAndOwnerId(id, ownerId);
        return cat;
    }

    @GetMapping(value = "/filter")
    public List<CatDto> getByColor(@RequestParam("color") String color) {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int ownerId = myUserDetails.getUser().getOwner().getId();
        List<CatDto> res = catService.findCatsByColorAndOwnerId(color, ownerId);
        return res;
    }
}
