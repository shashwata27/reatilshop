package com.pet.project.retailshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Column
    @Getter
    @Setter
    private int item_id;

    @Column
    @Getter
    @Setter
    private int stock;

    @Column(name = "last_restocking_date")
    @Getter
    @Setter
    private Date lastRestockingDate;

    public Inventory() {
    }

    public Inventory(int item_id, int stock, Date lastRestockingDate) {
        this.item_id = item_id;
        this.stock = stock;
        this.lastRestockingDate = lastRestockingDate;
    }

}
