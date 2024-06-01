package com.crud_project.service;

import com.crud_project.exception.ResourceNotFoundException;
import com.crud_project.model.entity.Document;
import com.crud_project.model.entity.Role;
import com.crud_project.model.entity.User;
import com.crud_project.model.enums.ERole;
import com.crud_project.model.enums.Status;
import com.crud_project.model.request.DocumentDetails;
import com.crud_project.repository.DocumentRepository;
import com.crud_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    UserRepository userRepository;


    public Document addDocument(String username, DocumentDetails documentDetails) {
        Document document = new Document();
        document.setSupplyDocumentName(documentDetails.getName());
        document.setSupplyDocumentSubject(documentDetails.getSubject());
        document.setCreatedBy(username);
        document.setStatus(Status.PENDING);
        document.setCreatedOn(LocalDateTime.now());
        document.setWarehouse(documentDetails.getWarehouse());
        document.setItem(documentDetails.getItem());
        return documentRepository.save(document);

    }

    public List<Document> getAllDocumentByUsername(String username) {
        User user = getUserIdByUsername(username);
        boolean isEmployee = user.getRoles().stream()
                .map(Role::getName)
                .anyMatch(roleName -> roleName.equals(ERole.ROLE_EMPLOYEE));
        if(isEmployee){
            return documentRepository.findByCreatedBy(username);
        }
        return documentRepository.findAll();
    }


    public User getUserIdByUsername(String username){
        return userRepository.findByUsername(username).get();
    }

    public Document getDocumentById(Long documentId) {
        return documentRepository.findById(documentId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + documentId));

    }

    public List<Document> getDocumentByIds(List<Long> documentIds) {
        return documentRepository.findAllById(documentIds);

    }

    public Document updateDocument(Document document, DocumentDetails documentDetails) {
        document.setSupplyDocumentName(documentDetails.getName());
        document.setSupplyDocumentSubject(documentDetails.getSubject());
        document.setStatus(Status.valueOf(documentDetails.getStatus()));
        document.setWarehouse(documentDetails.getWarehouse());
        document.setItem(documentDetails.getItem());
        return documentRepository.save(document);
    }

    public void deleteDocument(Document document) {
        documentRepository.delete(document);
    }

    public void deleteDocumentByIds(List<Document> documents) {
        documentRepository.deleteAll(documents);
    }
}
