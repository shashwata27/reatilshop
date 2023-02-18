package com.pet.project.retailshop.repository;

import com.pet.project.retailshop.model.StoreOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreOrderRepository extends JpaRepository<StoreOrder,Integer> {

}
