package ru.itmo.kotiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dao.CatDao;
import ru.itmo.kotiki.entity.Cat;
import ru.itmo.kotiki.entity.Color;
import ru.itmo.kotiki.service.dto.CatDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatServiceImpl implements CatService {

    private final CatDao catDao;

    @Autowired
    public CatServiceImpl(CatDao catDao) {
        this.catDao = catDao;
    }

    public CatDto getCat(int id) {
        Cat cat = catDao.getById(id);
        return new CatDto(cat);
    }

    public CatDto saveCat(Cat cat) {
        var tmpCat = catDao.save(cat);
        return new CatDto(tmpCat);
    }

    public void deleteCat(Cat cat) {
        catDao.delete(cat);
    }

    public List<CatDto> findAllCats() {
        List<Cat> cats = catDao.findAll();
        List<CatDto> tmp = new ArrayList<>();
        for (Cat cat : cats) {
            tmp.add(new CatDto(cat));
        }
        return tmp;
    }

    public List<CatDto> findCatsByColor(String color) {
        List<Cat> cats = catDao.findCatsByColor(Color.valueOf(color.toUpperCase()));
        List<CatDto> tmp = new ArrayList<>();
        for (int i = 0; i < cats.size(); i++) {
            tmp.add(new CatDto(cats.get(i)));
        }
        return tmp;
    }

    @Override
    public List<CatDto> findAllByOwnerId(int ownerId) {
        List<Cat> cats = catDao.findAllByOwnerId(ownerId);
        List<CatDto> tmp = new ArrayList<>();
        for (int i = 0; i < cats.size(); i++) {
            tmp.add(new CatDto(cats.get(i)));
        }
        return tmp;
    }

    @Override
    public CatDto findByIdAndOwnerId(int id, int ownerId) {
        Cat cat = catDao.findByIdAndOwnerId(id, ownerId);
        return new CatDto(cat);
    }

    @Override
    public List<CatDto> findCatsByColorAndOwnerId(String name, int ownerId) {
        List<Cat> cats = catDao.findCatsByColorAndOwnerId(name, ownerId);
        List<CatDto> tmp = new ArrayList<>();
        for (int i = 0; i < cats.size(); i++) {
            tmp.add(new CatDto(cats.get(i)));
        }
        return tmp;
    }

}
