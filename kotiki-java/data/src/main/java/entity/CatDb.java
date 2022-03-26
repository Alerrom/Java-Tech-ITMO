package entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cats")
public class CatDb implements Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "breed")
    private String breed;

    @Column(name = "color")
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private OwnerDb owner;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<CatDb> friends;


    public CatDb() {
    }

    public CatDb(String name, Date birthday, String breed, Color color) {
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.color = color;
        this.friends = new HashSet<>();
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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(OwnerDb owner) {
        this.owner = owner;
    }

    public void addFriend(CatDb catDb) {
        this.friends.add(catDb);
    }

    public void deleteFriend(CatDb catDb) {
        this.friends.remove(catDb);
    }

    @Override
    public String toString() {
        return "models.Cat{" +
                "id = " + id +
                ", name = " + name +
                ", birthday = " + birthday +
                ", breed = " + breed +
                ", color = " + color +
                "}";
    }
}
