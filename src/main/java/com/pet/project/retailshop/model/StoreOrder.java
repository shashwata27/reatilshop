package com.pet.project.retailshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Entity
@Table(name = "store_order")
@NoArgsConstructor
public class StoreOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int order_id;

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

//  one order can have only one type of item for simplicity
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public StoreOrder(Float value, Date date, Boolean fulfilled, Item item, Customer customer) {
        this.value = value;
        this.date = date;
        this.fulfilled = fulfilled;
        this.item = item;
        this.customer = customer;
    }
}
