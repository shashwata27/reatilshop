package com.pet.project.retailshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;

@ToString
@Entity
@Table(name = "item")
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int item_id;

    @Column
    @Getter
    @Setter
    @Size(min = 3,message ="Name should be at least 3 characters.")
    private String name;

    @Column
    @Getter
    @Setter
    private String description;

    @Column
    @Getter
    @Setter
    private Float value;

    public Item(String name, String description, Float value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }


}
