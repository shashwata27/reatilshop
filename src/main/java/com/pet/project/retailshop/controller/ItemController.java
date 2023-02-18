package com.pet.project.retailshop.controller;

import com.pet.project.retailshop.exception.ResourseAlredyExistsException;
import com.pet.project.retailshop.exception.ResourseNotFoundException;
import com.pet.project.retailshop.model.Item;
import com.pet.project.retailshop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemRepository.findAll());
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemsById(@PathVariable int id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException(
                        MessageFormat.format("Item with id: {0} doesn't exist.", id))
                );
        return ResponseEntity.ok(item);
    }

    @PostMapping("/items")
    public ResponseEntity<List<Item>> createItem(@RequestBody List<Item> items) {
        List<Item> insertedItems = new ArrayList<>();
        List<Integer> presentItems = new ArrayList<>();
        items.forEach(item -> {
            if (itemRepository.findById(item.getItem_id()).isPresent()) presentItems.add(item.getItem_id());
        });
        if (!presentItems.isEmpty()) {
            throw new ResourseAlredyExistsException(presentItems);
        }
        items.forEach(item -> insertedItems.add(itemRepository.save(item)));
        return ResponseEntity.ok(insertedItems);
    }


    @RequestMapping(value={"items/{id}", "items/{id}/update"}, method={RequestMethod.PUT,RequestMethod.PATCH})
    public ResponseEntity<Item> updateItem(@PathVariable int id, @RequestBody Item itemDetails) {
        Item updatingItem = itemRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException(
                        MessageFormat.format("Item with id: {0} doesn't exist", id)
                ));

        if (itemDetails.getName() != null)
            updatingItem.setName(itemDetails.getName());
        if (itemDetails.getDescription() != null)
            updatingItem.setDescription(itemDetails.getDescription());
        if (itemDetails.getValue() != null)
            updatingItem.setValue(itemDetails.getValue());

        itemRepository.save(updatingItem);

        return ResponseEntity.ok(updatingItem);
    }



    @DeleteMapping("items/{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable int id) {
        Item deletingItem = itemRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException(
                        MessageFormat.format("Item with id: {0} doesn't exist", id)
                ));
        itemRepository.delete(deletingItem);
        return ResponseEntity.ok(deletingItem);
    }


}
