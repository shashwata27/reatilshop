package com.pet.project.retailshop.controller;

import com.pet.project.retailshop.model.StoreOrder;
import com.pet.project.retailshop.repository.StoreOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1")
public class StoreOrderController {

    @Autowired
    private StoreOrderRepository storeOrderRepository;

    @GetMapping("/orders")
    public ResponseEntity<List<StoreOrder>> getAllOrders() {
        return ResponseEntity.ok(storeOrderRepository.findAll());
    }
}
