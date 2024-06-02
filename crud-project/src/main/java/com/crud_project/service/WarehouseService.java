package com.crud_project.service;

import com.crud_project.exception.ResourceNotFoundException;
import com.crud_project.exception.WarehouseServiceException;
import com.crud_project.model.entity.Item;
import com.crud_project.model.entity.Role;
import com.crud_project.model.entity.User;
import com.crud_project.model.entity.Warehouse;
import com.crud_project.model.enums.ERole;
import com.crud_project.model.request.WarehouseDetails;
import com.crud_project.repository.UserRepository;
import com.crud_project.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WarehouseService {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseService.class);

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    UserRepository userRepository;

    public Warehouse addWarehouse(String username, WarehouseDetails warehouseDetails) {
        logger.info("Adding new warehouse with details: {}", warehouseDetails);
        try {
            Warehouse warehouse = new Warehouse();
            warehouse.setName(warehouseDetails.getName());
            warehouse.setDescription(warehouseDetails.getDescription());
            warehouse.setCreatedBy(username);
            warehouse.setCreatedOn(LocalDateTime.now());
            for (Item item : warehouseDetails.getItems()) {
                item.setWarehouse(warehouse);
                warehouse.getItems().add(item);
            }

            Warehouse savedWarehouse = warehouseRepository.save(warehouse);
            logger.info("Warehouse successfully added with ID: {}", savedWarehouse.getId());
            return savedWarehouse;
        } catch (Exception e) {
            logger.error("Error adding warehouse: {}", e.getMessage(), e);
            throw new WarehouseServiceException("Error adding warehouse", e);
        }
    }

    public List<Warehouse> getAllWarehouseByUsername(String username) {
        logger.info("Fetching all warehouses for user: {}", username);
        try {
            User user = getUserIdByUsername(username);
            boolean isEmployee = user.getRoles().stream()
                    .map(Role::getName)
                    .anyMatch(roleName -> roleName.equals(ERole.ROLE_EMPLOYEE));

            List<Warehouse> warehouses;
            if (isEmployee) {
                warehouses = warehouseRepository.findAll();
                logger.info("User is an employee, returning all warehouses. Total: {}", warehouses.size());
            } else {
                warehouses = warehouseRepository.findByCreatedBy(username);
                logger.info("User is not an employee, returning own warehouses. Total: {}", warehouses.size());
            }

            return warehouses;
        } catch (Exception e) {
            logger.error("Error fetching warehouses for user {}: {}", username, e.getMessage(), e);
            throw new WarehouseServiceException("Error fetching warehouses", e);
        }
    }

    public User getUserIdByUsername(String username) {
        logger.info("Fetching user by username: {}", username);
        try {
            return userRepository.findByUsername(username).orElseThrow(() -> {
                logger.error("User not found with username: {}", username);
                return new ResourceNotFoundException("User not found with username: " + username);
            });
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error fetching user by username {}: {}", username, e.getMessage(), e);
            throw new WarehouseServiceException("Error fetching user by username", e);
        }
    }

    public Warehouse getWarehouseById(Long warehouseId) {
        logger.info("Fetching warehouse by ID: {}", warehouseId);
        try {
            return warehouseRepository.findById(warehouseId)
                    .orElseThrow(() -> {
                        logger.error("Warehouse not found with ID: {}", warehouseId);
                        return new ResourceNotFoundException("Warehouse not exist with id: " + warehouseId);
                    });
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error fetching warehouse by ID {}: {}", warehouseId, e.getMessage(), e);
            throw new WarehouseServiceException("Error fetching warehouse by ID", e);
        }
    }

    public List<Warehouse> getWarehouseByIds(List<Long> warehouseIds) {
        logger.info("Fetching warehouses by IDs: {}", warehouseIds);
        try {
            return warehouseRepository.findAllById(warehouseIds);
        } catch (Exception e) {
            logger.error("Error fetching warehouses by IDs {}: {}", warehouseIds, e.getMessage(), e);
            throw new WarehouseServiceException("Error fetching warehouses by IDs", e);
        }
    }

    public Warehouse updateWarehouse(Warehouse warehouse, WarehouseDetails warehouseDetails) {
        logger.info("Updating warehouse with ID: {}", warehouse.getId());
        try {
            warehouse.setName(warehouseDetails.getName());
            warehouse.setDescription(warehouseDetails.getDescription());
            List<Item> items = warehouseDetails.getItems().stream()
                    .peek(item -> item.setWarehouse(warehouse))
                    .collect(Collectors.toList());
            warehouse.setItems(items);

            Warehouse updatedWarehouse = warehouseRepository.save(warehouse);
            logger.info("Warehouse successfully updated with ID: {}", updatedWarehouse.getId());
            return updatedWarehouse;
        } catch (Exception e) {
            logger.error("Error updating warehouse with ID {}: {}", warehouse.getId(), e.getMessage(), e);
            throw new WarehouseServiceException("Error updating warehouse", e);
        }
    }

    public void deleteWarehouse(Warehouse warehouse) {
        logger.info("Deleting warehouse with ID: {}", warehouse.getId());
        try {
            warehouseRepository.delete(warehouse);
            logger.info("Warehouse successfully deleted with ID: {}", warehouse.getId());
        } catch (Exception e) {
            logger.error("Error deleting warehouse with ID {}: {}", warehouse.getId(), e.getMessage(), e);
            throw new WarehouseServiceException("Error deleting warehouse", e);
        }
    }

    public void deleteWarehouseByIds(List<Warehouse> warehouses) {
        List<Long> warehouseIds = warehouses.stream().map(Warehouse::getId).collect(Collectors.toList());
        logger.info("Deleting warehouses with IDs: {}", warehouseIds);
        try {
            warehouseRepository.deleteAll(warehouses);
            logger.info("Warehouses successfully deleted with IDs: {}", warehouseIds);
        } catch (Exception e) {
            logger.error("Error deleting warehouses with IDs {}: {}", warehouseIds, e.getMessage(), e);
            throw new WarehouseServiceException("Error deleting warehouses", e);
        }
    }

    public Optional<Warehouse> getWarehouseByName(String name) {
        logger.info("Fetching warehouse by name: {}", name);
        try {
            return warehouseRepository.findByName(name);
        } catch (Exception e) {
            logger.error("Error fetching warehouse by name {}: {}", name, e.getMessage(), e);
            throw new WarehouseServiceException("Error fetching warehouse by name", e);
        }
    }
}
