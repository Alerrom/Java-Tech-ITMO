package ru.itmo.kotiki.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.entity.Cat;
import ru.itmo.kotiki.entity.Color;

import java.util.List;

@Repository
public interface CatDao extends JpaRepository<Cat, Integer> {
    List<Cat> findCatsByColor(Color color);

    List<Cat> findAllByOwnerId(int ownerId);

    Cat findByIdAndOwnerId(int id, int ownerId);

    List<Cat> findCatsByColorAndOwnerId(String name, int ownerId);
}
