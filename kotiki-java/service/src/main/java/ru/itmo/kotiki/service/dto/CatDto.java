package ru.itmo.kotiki.service.dto;

import ru.itmo.kotiki.entity.Color;
import ru.itmo.kotiki.entity.Cat;

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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Color getColor() {
        return color;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getBreed() {
        return breed;
    }
}
