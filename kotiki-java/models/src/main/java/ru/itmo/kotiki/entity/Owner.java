package ru.itmo.kotiki.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owners")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private Date birthday;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Cat> cats;

    public Owner() {
    }

    public Owner(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
        this.cats = new ArrayList<>();
    }

    public void addCat(Cat cat) {
        cat.setOwner(this);
        cats.add(cat);
    }

    public void removeCat(Cat cat) {
        cats.remove(cat);
    }

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

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
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
