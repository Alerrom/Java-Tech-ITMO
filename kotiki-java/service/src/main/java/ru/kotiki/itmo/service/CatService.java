package ru.kotiki.itmo.service;

import ru.kotiki.itmo.entity.Cat;
import ru.kotiki.itmo.service.dto.CatDto;

import java.util.List;

public interface CatService {

    CatDto getCat(int id);

    CatDto saveCat(Cat cat);

    void deleteCat(Cat cat);

    List<CatDto> findAllCats();

    List<CatDto> findAllCatsByColor(String color);
}
