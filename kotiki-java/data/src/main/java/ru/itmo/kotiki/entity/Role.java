package ru.itmo.kotiki.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Roles role;

    public Role() {

    }

    public Role(Long id, Roles role) {
        this.id = id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }


    public Roles getRole() {
        return role;
    }
}
