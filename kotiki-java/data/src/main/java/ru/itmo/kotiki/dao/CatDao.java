package ru.itmo.kotiki.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.entity.Cat;
import ru.itmo.kotiki.entity.Color;

import java.util.List;

@Repository
public interface CatDao extends JpaRepository<Cat, Integer> {
    @Query("select c from Cat c where c.color = ?1")
    List<Cat> findCatsByColor(Color color);

    List<Cat> findAllByOwnerId(int ownerId);

    Cat findByIdAndOwnerId(int id, int ownerId);

    List<Cat> findCatsByColorAndOwnerId(String name, int ownerId);
}
