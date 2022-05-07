package ru.kotiki.itmo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kotiki.itmo.service.OwnerService;
import ru.kotiki.itmo.service.dto.OwnerDto;

import java.util.List;

@RestController
@RequestMapping("owners")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public List<OwnerDto> getAll() {
        List<OwnerDto> owners = ownerService.findAllOwners();
        return owners;
    }

    @GetMapping(value = "/{id}")
    public OwnerDto getById(@RequestParam("id") Integer id) {
        OwnerDto owner = ownerService.getOwner(id);
        return owner;
    }

    @GetMapping(value = "/filter")
    public List<OwnerDto> getByName(String name) {
        List<OwnerDto> owners = ownerService.findOwnersByName(name);
        return owners;
    }
}
