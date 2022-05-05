package ru.kotiki.itmo.service.dto;

import ru.kotiki.itmo.entity.Cat;
import ru.kotiki.itmo.entity.Color;
import ru.kotiki.itmo.entity.Owner;

import java.sql.Date;

public class CatDto {
    private int id;
    private String name;
    private Date birthday;
    private String breed;
    private Color color;
    private int ownerId;

    public CatDto(Cat cat) {
        this.id = cat.getId();
        this.name = cat.getName();
        this.birthday = cat.getBirthday();
        this.breed = cat.getBreed();
        this.color = cat.getColor();
        this.ownerId = cat.getOwner().getId();
    }
}
