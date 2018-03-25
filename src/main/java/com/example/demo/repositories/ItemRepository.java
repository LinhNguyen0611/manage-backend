package com.example.demo.repositories;

import com.example.demo.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Item repository.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    // Additional repository method. The insert, get list, get one, update, delete is provided
}
