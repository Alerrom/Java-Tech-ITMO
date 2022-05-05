package ru.kotiki.itmo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kotiki.itmo.entity.Cat;

@Repository
public interface CatDao extends JpaRepository<Cat, Integer> {
}
