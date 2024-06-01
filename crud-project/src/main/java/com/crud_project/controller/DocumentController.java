package com.crud_project.controller;

import com.crud_project.model.entity.Document;
import com.crud_project.model.request.DocumentDetails;
import com.crud_project.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/document/")
public class DocumentController {

    @Autowired
    DocumentService documentService;


    @GetMapping()
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER')")
    public ResponseEntity<List<Document>> getAllDocument(@AuthenticationPrincipal UserDetails userDetails) {
        List<Document> documentList = documentService.getAllDocumentByUsername(userDetails.getUsername());
        documentList.sort(Comparator.comparing(Document::getCreatedOn).reversed());
        return ResponseEntity.ok(documentList);
    }

    @PostMapping()
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<Document> addDocument(@AuthenticationPrincipal UserDetails userDetails ,
                                                  @RequestBody DocumentDetails documentDetails) {
        Document warehouse = documentService.addDocument(userDetails.getUsername() , documentDetails);
        return ResponseEntity.ok(warehouse);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<Document> getDocumentById(@PathVariable Long id) {
        Document warehouse = documentService.getDocumentById(id);
        return ResponseEntity.ok(warehouse);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('EMPLOYEE')")
    public ResponseEntity<Document> updateDocument(@PathVariable Long id,
                                                   @RequestBody DocumentDetails documentDetails) {
        Document warehouse = documentService.getDocumentById(id);
        Document updatedWarehouse = documentService.updateDocument(warehouse , documentDetails);
        return ResponseEntity.ok(updatedWarehouse);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<String> deleteDocument(@PathVariable Long id) {
        Document warehouse = documentService.getDocumentById(id);
        documentService.deleteDocument(warehouse);
        return ResponseEntity.ok("deleted");
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<String> deleteDocumentByIds(@RequestBody List<Long> ids) {
        List<Document> warehouses = documentService.getDocumentByIds(ids);
        documentService.deleteDocumentByIds(warehouses);
        return ResponseEntity.ok("deleted");
    }
}
