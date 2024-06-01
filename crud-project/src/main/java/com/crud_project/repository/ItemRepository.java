package com.crud_project.repository;

import com.crud_project.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item , Long> {
    List<Item> findByWarehouseId(Long id);
}
