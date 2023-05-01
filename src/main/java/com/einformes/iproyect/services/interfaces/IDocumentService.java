package com.einformes.iproyect.services.interfaces;

import com.einformes.iproyect.controller.dtos.requests.Document.CreateDocumentRequest;
import com.einformes.iproyect.controller.dtos.requests.Document.UpdateDocumentRequest;
import com.einformes.iproyect.controller.dtos.responses.Document.GetDocumentResponse;


import java.util.List;

public interface IDocumentService {
    GetDocumentResponse getOneDocument(Long IdDocument);
    List<GetDocumentResponse> getAllDocuments();
    void createDocument(CreateDocumentRequest request);
    GetDocumentResponse updateDocument(UpdateDocumentRequest request, Long IdDocument);
    void deleteDocument(Long id);
}
