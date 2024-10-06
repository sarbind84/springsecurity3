package com.inatializ.inatializ.entity;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "products")
public class Products {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;
    @Column(name = "name")
    public String name;
    @Column(name = "description")
    public String description;
    @Column(name = "price")
    public Double price;
    @Column(name = "quantity")
    public Integer quantity;

}
