package com.indit.pricelist.contexts.shop.pricelists.infra.JPA;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "brands")
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
}
