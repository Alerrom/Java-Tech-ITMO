package dao;

import entity.OwnerDb;

import java.util.List;

public interface OwnerDao {
    OwnerDb findById(int id);

    void save(OwnerDb ownerDb);

    void update(OwnerDb ownerDb);

    void delete(OwnerDb ownerDb);

    List<OwnerDb> findAll();
}
