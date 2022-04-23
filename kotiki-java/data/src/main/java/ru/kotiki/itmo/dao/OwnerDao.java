package ru.kotiki.itmo.dao;

import ru.kotiki.itmo.entity.Owner;

import java.util.List;

public interface OwnerDao {
    Owner findById(int id);

    void save(Owner owner);

    void update(Owner owner);

    void delete(Owner owner);

    List<Owner> findAll();
}
