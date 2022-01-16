package com.geekbrains.spring.web.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "units")
@NoArgsConstructor
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "unit")
    private List<Product> unit;

    public Unit(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
