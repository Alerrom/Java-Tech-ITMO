package ru.kotiki.itmo.service;

import ru.kotiki.itmo.entity.Owner;
import ru.kotiki.itmo.service.dto.OwnerDto;

import java.util.List;

public interface OwnerService {
    OwnerDto getOwner(int id);

    OwnerDto saveOwner(Owner owner);

    void deleteOwner(Owner owner);

    List<OwnerDto> findAllOwners();

    List<OwnerDto> findOwnersByName(String name);
}
