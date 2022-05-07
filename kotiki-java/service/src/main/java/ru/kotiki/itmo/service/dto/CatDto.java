package ru.kotiki.itmo.service.dto;

import ru.kotiki.itmo.entity.Cat;
import ru.kotiki.itmo.entity.Color;
import ru.kotiki.itmo.entity.Owner;

import java.sql.Date;

public class CatDto {
    private final int id;
    private final String name;
    private final Date birthday;
    private final String breed;
    private final Color color;
    private final int ownerId;

    public CatDto(Cat cat) {
        this.id = cat.getId();
        this.name = cat.getName();
        this.birthday = cat.getBirthday();
        this.breed = cat.getBreed();
        this.color = cat.getColor();
        this.ownerId = cat.getOwner().getId();
    }
}
