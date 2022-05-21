package ru.itmo.kotiki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dto.OwnerDto;
import ru.itmo.kotiki.entity.Owner;

import java.util.ArrayList;
import java.util.List;

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

    public Owner saveOwner(Owner owner) {
        var tmpOwner = ownerDao.save(owner);
        return tmpOwner;
    }

    public void deleteOwner(Owner owner) {
        ownerDao.delete(owner);
    }

    public List<OwnerDto> findAllOwners() {
        List<Owner> owners = ownerDao.findAll();
        List<OwnerDto> tmp = new ArrayList<>();
        for (Owner owner : owners) {
            tmp.add(new OwnerDto(owner));
        }
        return tmp;
    }

    @Override
    public List<OwnerDto> findOwnersByName(String name) {
        List<Owner> owners = ownerDao.findOwnersByName(name);
        List<OwnerDto> tmp = new ArrayList<>();
        for (int i = 0; i < owners.size(); i++) {
            tmp.add(new OwnerDto(owners.get(i)));
        }
        return tmp;
    }

}
