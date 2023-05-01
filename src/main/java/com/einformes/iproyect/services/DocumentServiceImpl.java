package com.einformes.iproyect.services;

import com.einformes.iproyect.controller.dtos.requests.Document.CreateDocumentRequest;
import com.einformes.iproyect.controller.dtos.requests.Document.UpdateDocumentRequest;
import com.einformes.iproyect.controller.dtos.responses.Document.GetDocumentResponse;
import com.einformes.iproyect.entities.Document;
import com.einformes.iproyect.repositories.IDocumentRepository;
import com.einformes.iproyect.services.interfaces.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements IDocumentService {
    @Autowired
    private IDocumentRepository repository;

    @Override
    public GetDocumentResponse getOneDocument(Long IdDocument) {
        return from(IdDocument);
    }
    private GetDocumentResponse from(Long idDocument){
        return repository.findById(idDocument)
                .map(this::from)
                .orElseThrow(() -> new RuntimeException("El documento no existe"));
    }
    private GetDocumentResponse from(Document document) {
        GetDocumentResponse response = new GetDocumentResponse();
        response.setId_document(document.getId_document());
        response.setDocument(document.getDocument());
        return response;
    }

    @Override
    public List<GetDocumentResponse> getAllDocuments() {
        return repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public void createDocument(CreateDocumentRequest request) {
        Document document = from(request);
        repository.save(document);
    }
    private Document from(CreateDocumentRequest request) {
        Document document = new Document();
        document.setDocument(request.getDocument());

        return document;
    }

    @Override
    public GetDocumentResponse updateDocument(UpdateDocumentRequest request, Long IdDocument) {
        Document document = findOneAndEnsureExist(IdDocument);
        document = update(document, request);
        return from(document);
    }
    private Document update(Document document, UpdateDocumentRequest request) {
        document.setDocument(request.getDocument());
        return repository.save(document);
    }

    private Document findOneAndEnsureExist(Long IdDocuemnt) {
        return repository.findById(IdDocuemnt)
                .orElseThrow(() -> new RuntimeException("El documento no existe"));
    }

    @Override
    public void deleteDocument(Long IdDocument) {
        repository.deleteById(IdDocument);
    }
}
