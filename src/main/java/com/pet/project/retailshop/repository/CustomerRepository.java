package com.pet.project.retailshop.repository;

import com.pet.project.retailshop.exception.ResourseNotFoundException;
import com.pet.project.retailshop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Optional<Customer> findByName(String name) throws ResourseNotFoundException;
    public Optional<List<Customer>> findAllByPhoneNumber(String phoneNumber);
}
