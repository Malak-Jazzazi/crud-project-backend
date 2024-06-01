package com.crud_project.service;

import com.crud_project.exception.ResourceNotFoundException;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    UserRepository userRepository;

    public Warehouse addWarehouse(String username, WarehouseDetails warehouseDetails) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseDetails.getName());
        warehouse.setDescription(warehouseDetails.getDescription());
        warehouse.setCreatedBy(username);
        warehouse.setCreatedOn(LocalDateTime.now());
        for (Item item:warehouseDetails.getItems()) {
            item.setWarehouse(warehouse);
            warehouse.getItems().add(item);
        }
        return warehouseRepository.save(warehouse);

    }

    public List<Warehouse> getAllWarehouseByUsername(String username) {
        User user = getUserIdByUsername(username);
        boolean isEmployee = user.getRoles().stream()
                .map(Role::getName)
                .anyMatch(roleName -> roleName.equals(ERole.ROLE_EMPLOYEE));
        if(isEmployee){
            return warehouseRepository.findAll();
        }
        return warehouseRepository.findByCreatedBy(username);
    }


    public User getUserIdByUsername(String username){
        return userRepository.findByUsername(username).get();
    }

    public Warehouse getWarehouseById(Long warehouseId) {
        return warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + warehouseId));

    }

    public List<Warehouse> getWarehouseByIds(List<Long> warehouseIds) {
        return warehouseRepository.findAllById(warehouseIds);

    }

    public Warehouse updateWarehouse(Warehouse warehouse, WarehouseDetails warehouseDetails) {
        warehouse.setName(warehouseDetails.getName());
        warehouse.setDescription(warehouseDetails.getDescription());
        List<Item> items = warehouseDetails.getItems().stream()
                .peek(item -> item.setWarehouse(warehouse))
                .collect(Collectors.toList());
        warehouse.setItems(items);

        return warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(Warehouse warehouse) {
        warehouseRepository.delete(warehouse);
    }

    public void deleteWarehouseByIds(List<Warehouse> warehouses) {
        warehouseRepository.deleteAll(warehouses);
    }

    public Optional<Warehouse> getWarehouseByName(String name) {
        return warehouseRepository.findByName(name);
    }
}
