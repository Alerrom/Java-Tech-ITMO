package ru.kotiki.itmo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kotiki.itmo.service.OwnerService;
import ru.kotiki.itmo.service.dto.OwnerDto;

import java.util.List;

@RestController
@RequestMapping("owners-rest")
public class OwnerController {
    private OwnerService ownerService;

    @GetMapping(produces = "application/json")
    public List<OwnerDto> getAll() {
        List<OwnerDto> owners = ownerService.findAllOwners();
        return owners;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public OwnerDto getById(@RequestParam("id") Integer id) {
        OwnerDto owner = ownerService.getOwner(id);
        return owner;
    }

    @GetMapping(value = "/filter", produces = "application/json")
    public List<OwnerDto> getByName(String name) {
        List<OwnerDto> owners = ownerService.findOwnersByName(name);
        return owners;
    }
}
