package com.pet.project.retailshop.controller;

import com.pet.project.retailshop.model.Inventory;
import com.pet.project.retailshop.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1")
public class InventoryController {
    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping({"/inventories", "/inventory"})
    public ResponseEntity<List<Inventory>> getAllInventory() {
        return ResponseEntity.ok(inventoryRepository.findAll());
    }
}
