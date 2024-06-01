package com.crud_project.repository;

import com.crud_project.model.entity.Document;
import com.crud_project.model.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByCreatedBy(String createdBy);

}
