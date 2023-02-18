package com.pet.project.retailshop.controller;

import com.pet.project.retailshop.exception.ResourseAlredyExistsException;
import com.pet.project.retailshop.exception.ResourseNotFoundException;
import com.pet.project.retailshop.model.Customer;
import com.pet.project.retailshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerRepository.findAll());
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomersById(@PathVariable int id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException(
                        MessageFormat.format("Customer with id: {0} doesn't exist.", id))
                );
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/customers")
    public ResponseEntity<List<Customer>> createCustomer(@RequestBody List<Customer> customers) {
        List<Customer> insertedCustomers = new ArrayList<>();
        List<Integer> presentCustomers = new ArrayList<>();
        customers.forEach(customer -> {
            if (customerRepository.findById(customer.getCustomer_id()).isPresent()) presentCustomers.add(customer.getCustomer_id());
        });
        if (!presentCustomers.isEmpty()) {
            throw new ResourseAlredyExistsException(presentCustomers);
        }
        customers.forEach(customer -> insertedCustomers.add(customerRepository.save(customer)));
        return ResponseEntity.ok(insertedCustomers);
    }

    @RequestMapping(value={"/customers/{id}", "/customers/{id}/update"}, method={RequestMethod.PUT,RequestMethod.PATCH})
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customerDetails) {
        Customer updatingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException(
                        MessageFormat.format("Customer with id: {0} doesn't exist", id)
                ));

        if (customerDetails.getName() != null)
            updatingCustomer.setName(customerDetails.getName());
        if (customerDetails.getAddress() != null)
            updatingCustomer.setAddress(customerDetails.getAddress());
        if (customerDetails.getPhoneNumber() != null)
            updatingCustomer.setPhoneNumber(customerDetails.getPhoneNumber());

        customerRepository.save(updatingCustomer);

        return ResponseEntity.ok(updatingCustomer);
    }



    @DeleteMapping("customers/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable int id) {
        Customer deletingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException(
                        MessageFormat.format("Customer with id: {0} doesn't exist", id)
                ));
        customerRepository.delete(deletingCustomer);
        return ResponseEntity.ok(deletingCustomer);
    }


}
