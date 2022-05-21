package ru.itmo.kotiki;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.entity.Owner;

import java.util.List;

@Repository
public interface OwnerDao extends JpaRepository<Owner, Integer> {
    List<Owner> findOwnersByName(String name);
}
