package ru.kotiki.itmo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotiki.itmo.entity.Owner;

public interface OwnerDao extends JpaRepository<Owner, Integer> {
}
