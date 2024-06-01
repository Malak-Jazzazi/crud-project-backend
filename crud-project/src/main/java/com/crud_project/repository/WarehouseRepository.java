package com.crud_project.repository;

import com.crud_project.model.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse , Long> {
    List<Warehouse> findByCreatedBy(String createdBy);
    Boolean existsByName(String username);

    Optional<Warehouse>  findByName(String username);

}
