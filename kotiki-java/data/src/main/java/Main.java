import dao.CatDaoImpl;
import dao.OwnerDaoImpl;
import entity.CatDb;
import entity.Color;
import entity.OwnerDb;
import service.CatService;
import service.OwnerService;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        /*
        OwnerService ownerService = new OwnerService(new OwnerDaoImpl());
        CatService catService = new CatService(new CatDaoImpl());

        OwnerDb owner1 = new OwnerDb("Alex", Date.valueOf("2002-01-03"));
        ownerService.saveOwner(owner1);

        CatDb cat1 = new CatDb("Anna", Date.valueOf("2022-01-03"), "A", Color.Purple);
        CatDb cat2 = new CatDb("Zhanna", Date.valueOf("2022-05-03"), "AB", Color.Green);

        catService.saveCat(cat1);
        catService.saveCat(cat2);

        cat1.addFriend(cat2);

        catService.updateCat(cat1);

        owner1.addCat(cat1);
        owner1.addCat(cat2);
        ownerService.updateOwner(owner1);
        */
    }
}
