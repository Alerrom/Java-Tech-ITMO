package dao;

import entity.CatDb;

import java.util.List;

public interface CatDao {
    CatDb findById(int id);
    void save(CatDb catDb);

    void update(CatDb catDb);

    void delete(CatDb catDb);

    List<CatDb> findAll();

    List<CatDb> findCatFriends();
}
