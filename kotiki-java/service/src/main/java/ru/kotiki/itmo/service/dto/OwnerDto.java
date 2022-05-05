package ru.kotiki.itmo.service.dto;

import ru.kotiki.itmo.entity.Owner;

import java.sql.Date;

public class OwnerDto {
    private int id;
    private String name;
    private Date birthday;

    public OwnerDto(Owner owner) {
        this.id = owner.getId();
        this.name = owner.getName();
        this.birthday = owner.getBirthday();
    }
}
