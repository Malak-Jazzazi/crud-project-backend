package com.crud_project.model.entity;

import com.crud_project.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String supplyDocumentName;

    private String supplyDocumentSubject;

    private String createdBy;

    private LocalDateTime createdOn;

    private Status status;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSupplyDocumentName() {
        return supplyDocumentName;
    }

    public void setSupplyDocumentName(String supplyDocumentName) {
        this.supplyDocumentName = supplyDocumentName;
    }

    public String getSupplyDocumentSubject() {
        return supplyDocumentSubject;
    }

    public void setSupplyDocumentSubject(String supplyDocumentSubject) {
        this.supplyDocumentSubject = supplyDocumentSubject;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
