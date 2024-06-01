package com.crud_project.controller;

import com.crud_project.model.entity.Warehouse;
import com.crud_project.model.request.WarehouseDetails;
import com.crud_project.payload.response.MessageResponse;
import com.crud_project.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/v1/warehouse/")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;


    @GetMapping()
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER')")
    public ResponseEntity<List<Warehouse>> getAllWarehouse(@AuthenticationPrincipal UserDetails userDetails) {
        List<Warehouse> warehouseList = warehouseService.getAllWarehouseByUsername(userDetails.getUsername());
        warehouseList.sort(Comparator.comparing(Warehouse::getCreatedOn).reversed());
        return ResponseEntity.ok(warehouseList);
    }

    @PostMapping()
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity addWarehouse(@AuthenticationPrincipal UserDetails userDetails ,
                                       @Valid @RequestBody WarehouseDetails warehouseDetails) {

        if(warehouseService.getWarehouseByName(warehouseDetails.getName()).isPresent()){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Warehouse Name is already taken!"));
        }
        Warehouse warehouse = warehouseService.addWarehouse(userDetails.getUsername() , warehouseDetails);
        return ResponseEntity.ok(warehouse);
    }


    @GetMapping("{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {
        Warehouse warehouse = warehouseService.getWarehouseById(id);
        return ResponseEntity.ok(warehouse);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity updateWarehouse(@PathVariable Long id,
                                          @RequestBody WarehouseDetails warehouseDetails) {
        Optional<Warehouse> warehouseWithSameName = warehouseService.getWarehouseByName(warehouseDetails.getName());
        if (warehouseWithSameName.isPresent() && !warehouseWithSameName.get().getId().equals(id)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Warehouse Name is already taken!"));
        }
        Warehouse warehouse = warehouseService.getWarehouseById(id);
        Warehouse updatedWarehouse =warehouseService.updateWarehouse(warehouse , warehouseDetails);
        return ResponseEntity.ok(updatedWarehouse);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> deleteWarehouse(@PathVariable Long id) {
        Warehouse warehouse = warehouseService.getWarehouseById(id);
        warehouseService.deleteWarehouse(warehouse);
        return ResponseEntity.ok("deleted");
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> deleteWarehouseByIds(@RequestBody List<Long> ids) {
        List<Warehouse> warehouses = warehouseService.getWarehouseByIds(ids);
        warehouseService.deleteWarehouseByIds(warehouses);
        return ResponseEntity.ok("deleted");
    }
}
