package ru.itmo.kotiki.service;

import ru.itmo.kotiki.dto.OwnerDto;
import ru.itmo.kotiki.entity.Owner;

import java.util.List;

public interface OwnerService {
    OwnerDto getOwner(int id);

    Owner saveOwner(Owner owner);

    void deleteOwner(Owner owner);

    List<OwnerDto> findAllOwners();

    List<OwnerDto> findOwnersByName(String name);
}
