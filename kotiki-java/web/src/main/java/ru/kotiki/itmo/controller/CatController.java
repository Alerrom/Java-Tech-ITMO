package ru.kotiki.itmo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kotiki.itmo.service.CatService;
import ru.kotiki.itmo.service.dto.CatDto;

import java.util.List;

@RestController
@RequestMapping("cats-rest")
public class CatController {
    private CatService catService;

    @GetMapping(produces = "application/json")
    public List<CatDto> getAll() {
        List<CatDto> cats = catService.findAllCats();
        return cats;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public CatDto getById(@RequestParam("id") Integer id) {
        CatDto cat = catService.getCat(id);
        return cat;
    }

    @GetMapping(value = "/filter", produces = "application/json")
    public List<CatDto> getByColor(@RequestParam("color") String color) {
        List<CatDto> cats = catService.findAllCatsByColor(color);
        return cats;
    }
}
