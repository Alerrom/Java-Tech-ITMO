package ru.kotiki.itmo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.kotiki.itmo.dao.CatDaoImpl;
import ru.kotiki.itmo.dao.OwnerDaoImpl;
import ru.kotiki.itmo.entity.CatDb;
import ru.kotiki.itmo.entity.Color;
import ru.kotiki.itmo.entity.OwnerDb;
import ru.kotiki.itmo.service.CatService;
import ru.kotiki.itmo.service.OwnerService;

import java.sql.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DaoServiceTest {
    private CatService catService;
    private OwnerService ownerService;
    private CatDb cat1;
    private CatDb cat2;
    private CatDb cat3;
    private OwnerDb owner1;
    private OwnerDb owner2;

    @Before
    public void setUp() {
        catService = new CatService(new CatDaoImpl());
        ownerService = new OwnerService(new OwnerDaoImpl());

        cat1 = new CatDb("Zoya", Date.valueOf("2022-03-02"), "A", Color.BLUE);
        cat2 = new CatDb("Anna", Date.valueOf("2020-03-02"), "AB", Color.PURPLE);
        cat3 = new CatDb("Zhanna", Date.valueOf("2018-03-02"), "AAB", Color.GREEN);

        owner1 = new OwnerDb("Alex", Date.valueOf("2002-06-22"));
        owner2 = new OwnerDb("Alina", Date.valueOf("2004-12-06"));
    }

    @Test
    public void addOwnerToDbCanFindOwner() {
        ownerService.saveOwner(owner1);
        var tmpOwner = ownerService.findOwner(owner1.getId());
        Assert.assertNotNull(tmpOwner);

        ownerService.saveOwner(owner2);
        tmpOwner = ownerService.findOwner(owner2.getId());
        Assert.assertNotNull(tmpOwner);
    }

    @Test
    public void addCatToDbCanFindCat() {
        catService.saveCat(cat1);
        var tmpCat = catService.findCat(cat1.getId());
        Assert.assertNotNull(tmpCat);

        catService.saveCat(cat2);
        tmpCat = catService.findCat(cat2.getId());
        Assert.assertNotNull(tmpCat);

        catService.saveCat(cat3);
        tmpCat = catService.findCat(cat3.getId());
        Assert.assertNotNull(tmpCat);
    }

    @Test
    public void createCatMockito() {
        cat1 = new CatDb("Zoya", Date.valueOf("2022-03-02"), "A", Color.BLUE);
        catService = mock(CatService.class);
        when(catService.findCat(cat1.getId())).thenReturn(cat1);
        Assert.assertEquals(cat1,catService.findCat(cat1.getId()));
    }
}
