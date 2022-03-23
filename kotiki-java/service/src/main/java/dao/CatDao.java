package dao;

import entity.Cat;

import java.util.List;

public interface CatDao {
    Cat findById(int id);

    void save(Cat cat);

    void update(Cat cat);

    void delete(Cat cat);

    List<Cat> findAll();

    List<Cat> findCatFriends();
}
