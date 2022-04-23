package ru.kotiki.itmo.service;

import ru.kotiki.itmo.dao.OwnerDao;
import ru.kotiki.itmo.entity.Owner;

import java.util.List;

public class OwnerServiceImpl implements OwnerService {
    private OwnerDao ownerDao;

    public OwnerServiceImpl(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    public Owner findOwner(int id) {
        return ownerDao.findById(id);
    }

    public void saveOwner(Owner owner) {
        ownerDao.save(owner);
    }

    public void updateOwner(Owner owner) {
        ownerDao.update(owner);
    }

    public void deleteOwner(Owner owner) {
        ownerDao.delete(owner);
    }

    public List<Owner> findAllOwners() {
        return ownerDao.findAll();
    }
}
