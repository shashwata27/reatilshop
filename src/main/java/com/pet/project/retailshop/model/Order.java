package com.pet.project.retailshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Column
    @Getter
    @Setter
    private Float value;

    @Column
    @Getter
    @Setter
    private Date date;

    @Column
    @Getter
    @Setter
    private Boolean fulfilled;

//    @Column(name = "customer_id")
//    @Getter
//    @Setter
//    private int customerId;

//    @Column(name = "item_id")
//    @Getter
//    @Setter
//    private int itemId;

    @Getter
    @Setter
    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Getter
    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;



    public Order() {
    }

    public Order(Float value, Date date, Boolean fulfilled, Item item, Customer customer) {
        this.value = value;
        this.date = date;
        this.fulfilled = fulfilled;
        this.item = item;
        this.customer = customer;
    }
}
