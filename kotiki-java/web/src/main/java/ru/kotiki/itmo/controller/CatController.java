package ru.kotiki.itmo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kotiki.itmo.service.CatService;
import ru.kotiki.itmo.service.dto.CatDto;

import java.util.List;

@RestController
@RequestMapping("cats")
public class CatController {
    private final CatService catService;
    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping
    public List<CatDto> getAll() {
        List<CatDto> cats = catService.findAllCats();
        return cats;
    }

    @GetMapping(value = "/{id}")
    public CatDto getById(@RequestParam("id") Integer id) {
        CatDto cat = catService.getCat(id);
        return cat;
    }

    @GetMapping(value = "/filter")
    public List<CatDto> getByColor(@RequestParam("color") String color) {
        List<CatDto> cats = catService.findAllCatsByColor(color);
        return cats;
    }
}
