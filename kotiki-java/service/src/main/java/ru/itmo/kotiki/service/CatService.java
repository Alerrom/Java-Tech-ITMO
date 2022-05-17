package ru.itmo.kotiki.service;

import ru.itmo.kotiki.entity.Cat;
import ru.itmo.kotiki.service.dto.CatDto;

import java.util.List;

public interface CatService {

    CatDto getCat(int id);

    CatDto saveCat(Cat cat);

    void deleteCat(Cat cat);

    List<CatDto> findAllCats();

    List<CatDto> findCatsByColor(String color);
}
