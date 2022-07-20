package ru.itmo.kotiki.dto;

import ru.itmo.kotiki.entity.Owner;

import java.sql.Date;

public class OwnerDto {
    private final int id;
    private final String name;
    private final Date birthday;

    public OwnerDto(Owner owner) {
        this.id = owner.getId();
        this.name = owner.getName();
        this.birthday = owner.getBirthday();
    }

    public Date getBirthday() {
        return birthday;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
