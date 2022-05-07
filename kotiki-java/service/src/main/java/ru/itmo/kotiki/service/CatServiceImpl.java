package ru.itmo.kotiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.dao.CatDao;
import ru.itmo.kotiki.entity.Cat;
import ru.itmo.kotiki.service.dto.CatDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CatServiceImpl implements CatService {
    private CatDao catDao;

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
        for (int i = 0; i < cats.size(); i++) {
            tmp.add(new CatDto(cats.get(i)));
        }
        return tmp;
    }

    public List<CatDto> findAllCatsByColor(String color) {
        List<Cat> cats = catDao.findAll();
        List<CatDto> tmp = new ArrayList<>();
        for (int i = 0; i < cats.size(); i++) {
            if (Objects.equals(color, cats.get(i).getColor().values())) {
                tmp.add(new CatDto(cats.get(i)));
            }
        }
        return tmp;
    }

}
