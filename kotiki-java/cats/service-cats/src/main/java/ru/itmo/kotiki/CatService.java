package ru.itmo.kotiki;


import ru.itmo.kotiki.dto.CatDto;
import ru.itmo.kotiki.entity.Cat;

import java.util.List;

public interface CatService {

    CatDto getCat(int id);

    CatDto saveCat(Cat cat);

    void deleteCat(Cat cat);

    List<CatDto> findAllCats();

    List<CatDto> findCatsByColor(String color);

    List<CatDto> findAllByOwnerId(int ownerId);

    CatDto findByIdAndOwnerId(int id, int ownerId);

    List<CatDto> findCatsByColorAndOwnerId(String name, int ownerId);
}
