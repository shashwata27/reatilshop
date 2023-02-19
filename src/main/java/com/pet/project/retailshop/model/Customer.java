package com.pet.project.retailshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;


@ToString
@Entity
@Table(name = "customer")
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int customer_id;

    @Column
    @Getter
    @Setter
    @Size(min = 3,message ="Name should be at least 3 characters.")
    private String name;

    @Column
    @Getter
    @Setter
    private String address;

    @Column
    @Getter
    @Setter
    @Size(min = 12,message ="PhoneNumber should be at least 12 digits.")
    private String phoneNumber;


    public Customer(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
