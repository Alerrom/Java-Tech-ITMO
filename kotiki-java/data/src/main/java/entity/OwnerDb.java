package entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owners")
public class OwnerDb implements Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Date birthday;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<CatDb> cats;

    public OwnerDb() {
    }

    public OwnerDb(String name, Date birthday){
        this.name = name;
        this.birthday = birthday;
        this.cats = new ArrayList<>();
    }

    public void addCat(CatDb cat) {
        cat.setOwner(this);
        cats.add(cat);
    }

    public void removeCat(CatDb cat) {
        cats.remove(cat);
    }

    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<CatDb> getCats() {
        return cats;
    }

    public void setCats(List<CatDb> cats) {
        this.cats = cats;
    }
    @Override
    public String toString() {
        return "models.Owner{" +
                "id = " + id +
                ", name = " + name +
                ", birthday = " + birthday +
                "}";
    }
}
