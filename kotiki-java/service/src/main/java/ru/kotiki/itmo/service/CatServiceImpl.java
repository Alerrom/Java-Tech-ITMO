package ru.kotiki.itmo.service;

import ru.kotiki.itmo.dao.CatDao;
import ru.kotiki.itmo.entity.Cat;

import java.util.List;

public class CatServiceImpl implements CatService {
    private CatDao catDao;

    public CatServiceImpl(CatDao catDao) {
        this.catDao = catDao;
    }

    public Cat findCat(int id) {
        return catDao.findById(id);
    }

    public void saveCat(Cat cat) {
        catDao.save(cat);
    }

    public void updateCat(Cat cat) {
        catDao.update(cat);
    }

    public void deleteCat(Cat cat) {
        catDao.delete(cat);
    }

    public List<Cat> findAllCats() {
        return catDao.findAll();
    }
}
