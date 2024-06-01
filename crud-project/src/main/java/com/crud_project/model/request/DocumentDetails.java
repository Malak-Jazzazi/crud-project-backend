package com.crud_project.model.request;

import com.crud_project.model.entity.Item;
import com.crud_project.model.entity.Warehouse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DocumentDetails {
    @JsonProperty("supplyDocumentName")
    private String name;
    @JsonProperty("supplyDocumentSubject")
    private String subject;

    private String status;

    private Warehouse warehouse;

    private Item item;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
