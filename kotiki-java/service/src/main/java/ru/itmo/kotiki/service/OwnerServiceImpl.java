package ru.itmo.kotiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dao.OwnerDao;
import ru.itmo.kotiki.entity.Owner;
import ru.itmo.kotiki.service.dto.OwnerDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OwnerServiceImpl implements OwnerService {
    private OwnerDao ownerDao;

    @Autowired
    public OwnerServiceImpl(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    public OwnerDto getOwner(int id) {
        Owner owner = ownerDao.getById(id);
        return new OwnerDto(owner);
    }

    public OwnerDto saveOwner(Owner owner) {
        var tmpOwner = ownerDao.save(owner);
        return new OwnerDto(tmpOwner);
    }

    public void deleteOwner(Owner owner) {
        ownerDao.delete(owner);
    }

    public List<OwnerDto> findAllOwners() {
        List<Owner> owners = ownerDao.findAll();
        List<OwnerDto> tmp = new ArrayList<>();
        for (int i = 0; i < owners.size(); i++) {
            tmp.add(new OwnerDto(owners.get(i)));
        }
        return tmp;
    }

    public List<OwnerDto> findOwnersByName(String name) {
        List<Owner> owners = ownerDao.findAll();
        List<OwnerDto> tmp = new ArrayList<>();
        for (int i = 0; i < owners.size(); i++) {
            if (Objects.equals(owners.get(i).getName(), name)) {
                tmp.add(new OwnerDto(owners.get(i)));
            }
        }
        return tmp;
    }
}
