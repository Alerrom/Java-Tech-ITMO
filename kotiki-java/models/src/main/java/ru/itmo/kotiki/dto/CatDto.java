package ru.itmo.kotiki.dto;

import ru.itmo.kotiki.entity.Cat;
import ru.itmo.kotiki.entity.Color;

import java.sql.Date;

public class CatDto {
    private final int id;
    private final String name;
    private final Date birthday;
    private final String breed;
    private final Color color;

    public CatDto(Cat cat) {
        this.id = cat.getId();
        this.name = cat.getName();
        this.birthday = cat.getBirthday();
        this.breed = cat.getBreed();
        this.color = cat.getColor();
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

    public String getBreed() {
        return breed;
    }
}
