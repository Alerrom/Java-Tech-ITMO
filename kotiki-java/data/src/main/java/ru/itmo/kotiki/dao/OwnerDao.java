package ru.itmo.kotiki.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.entity.Owner;

import java.util.List;

@Repository
public interface OwnerDao extends JpaRepository<Owner, Integer> {
    @Query("select o from Owner o where o.name = ?1")
    List<Owner> findOwnersByName(String name);
}
