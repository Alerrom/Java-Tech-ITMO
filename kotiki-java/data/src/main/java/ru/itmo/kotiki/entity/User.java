package ru.itmo.kotiki.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "flag")
    private boolean flag;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public User() {

    }

    public User(String name, String password, boolean flag, Role role, Owner owner) {
        this.name = name;
        this.password = password;
        this.flag = flag;
        this.role = role;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isFlag() {
        return flag;
    }

    public Role getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
