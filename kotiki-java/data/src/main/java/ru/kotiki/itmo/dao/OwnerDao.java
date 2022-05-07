package ru.kotiki.itmo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kotiki.itmo.entity.Owner;

@Repository
public interface OwnerDao extends JpaRepository<Owner, Integer> {
}
