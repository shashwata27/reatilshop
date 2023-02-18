package com.pet.project.retailshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@ToString
@Entity
@Table(name = "inventory")
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int inventory_id;

    @Getter
    @Setter
    private int stock;

    @Getter
    @Setter
    @Column(name = "last_restocking_date")
    private Date lastRestockingDate;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;


    public Inventory(int stock, Date lastRestockingDate, Item item) {
        this.item = item;
        this.stock = stock;
        this.lastRestockingDate = lastRestockingDate;
    }
}
