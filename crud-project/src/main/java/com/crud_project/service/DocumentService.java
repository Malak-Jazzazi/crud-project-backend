package com.crud_project.service;

import com.crud_project.exception.ResourceNotFoundException;
import com.crud_project.exception.DocumentServiceException;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    UserRepository userRepository;

    public Document addDocument(String username, DocumentDetails documentDetails) {
        logger.info("Adding new document with details: {}", documentDetails);
        try {
            Document document = new Document();
            document.setSupplyDocumentName(documentDetails.getName());
            document.setSupplyDocumentSubject(documentDetails.getSubject());
            document.setCreatedBy(username);
            document.setStatus(Status.PENDING);
            document.setCreatedOn(LocalDateTime.now());
            document.setWarehouse(documentDetails.getWarehouse());
            document.setItem(documentDetails.getItem());

            Document savedDocument = documentRepository.save(document);
            logger.info("Document successfully added with ID: {}", savedDocument.getId());
            return savedDocument;
        } catch (Exception e) {
            logger.error("Error adding document: {}", e.getMessage(), e);
            throw new DocumentServiceException("Error adding document", e);
        }
    }

    public List<Document> getAllDocumentByUsername(String username) {
        logger.info("Fetching all documents for user: {}", username);
        try {
            User user = getUserIdByUsername(username);
            boolean isEmployee = user.getRoles().stream()
                    .map(Role::getName)
                    .anyMatch(roleName -> roleName.equals(ERole.ROLE_EMPLOYEE));

            List<Document> documents;
            if (isEmployee) {
                documents = documentRepository.findAll();
                logger.info("User is an employee, returning all documents. Total: {}", documents.size());
            } else {
                documents = documentRepository.findByCreatedBy(username);
                logger.info("User is not an employee, returning own documents. Total: {}", documents.size());
            }

            return documents;
        } catch (Exception e) {
            logger.error("Error fetching documents for user {}: {}", username, e.getMessage(), e);
            throw new DocumentServiceException("Error fetching documents", e);
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
            throw new DocumentServiceException("Error fetching user by username", e);
        }
    }

    public Document getDocumentById(Long documentId) {
        logger.info("Fetching document by ID: {}", documentId);
        try {
            return documentRepository.findById(documentId)
                    .orElseThrow(() -> {
                        logger.error("Document not found with ID: {}", documentId);
                        return new ResourceNotFoundException("Document not exist with id: " + documentId);
                    });
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error fetching document by ID {}: {}", documentId, e.getMessage(), e);
            throw new DocumentServiceException("Error fetching document by ID", e);
        }
    }

    public List<Document> getDocumentByIds(List<Long> documentIds) {
        logger.info("Fetching documents by IDs: {}", documentIds);
        try {
            return documentRepository.findAllById(documentIds);
        } catch (Exception e) {
            logger.error("Error fetching documents by IDs {}: {}", documentIds, e.getMessage(), e);
            throw new DocumentServiceException("Error fetching documents by IDs", e);
        }
    }

    public Document updateDocument(Document document, DocumentDetails documentDetails) {
        logger.info("Updating document with ID: {}", document.getId());
        try {
            document.setSupplyDocumentName(documentDetails.getName());
            document.setSupplyDocumentSubject(documentDetails.getSubject());
            document.setStatus(Status.valueOf(documentDetails.getStatus()));
            document.setWarehouse(documentDetails.getWarehouse());
            document.setItem(documentDetails.getItem());

            Document updatedDocument = documentRepository.save(document);
            logger.info("Document successfully updated with ID: {}", updatedDocument.getId());
            return updatedDocument;
        } catch (Exception e) {
            logger.error("Error updating document with ID {}: {}", document.getId(), e.getMessage(), e);
            throw new DocumentServiceException("Error updating document", e);
        }
    }

    public void deleteDocument(Document document) {
        logger.info("Deleting document with ID: {}", document.getId());
        try {
            documentRepository.delete(document);
            logger.info("Document successfully deleted with ID: {}", document.getId());
        } catch (Exception e) {
            logger.error("Error deleting document with ID {}: {}", document.getId(), e.getMessage(), e);
            throw new DocumentServiceException("Error deleting document", e);
        }
    }

    public void deleteDocumentByIds(List<Document> documents) {
        List<Long> documentIds = documents.stream().map(Document::getId).collect(Collectors.toList());
        logger.info("Deleting documents with IDs: {}", documentIds);
        try {
            documentRepository.deleteAll(documents);
            logger.info("Documents successfully deleted with IDs: {}", documentIds);
        } catch (Exception e) {
            logger.error("Error deleting documents with IDs {}: {}", documentIds, e.getMessage(), e);
            throw new DocumentServiceException("Error deleting documents", e);
        }
    }
}
