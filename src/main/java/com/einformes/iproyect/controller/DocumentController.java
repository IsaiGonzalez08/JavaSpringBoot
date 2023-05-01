package com.einformes.iproyect.controller;

import com.einformes.iproyect.controller.dtos.requests.Document.CreateDocumentRequest;
import com.einformes.iproyect.controller.dtos.requests.Document.UpdateDocumentRequest;
import com.einformes.iproyect.controller.dtos.responses.Document.GetDocumentResponse;
import com.einformes.iproyect.services.interfaces.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("document")
public class DocumentController {

    @Autowired
    private IDocumentService service;

    @GetMapping("{id}")
    public GetDocumentResponse getOneDocument(@PathVariable Long id) {
        return service.getOneDocument(id);
    }
    @GetMapping("list")
    public List<GetDocumentResponse> getAllDocuments() {
        return service.getAllDocuments();
    }
    @PostMapping
    public void createDocument(@RequestBody CreateDocumentRequest request) {
         service.createDocument(request);
    }

    @PutMapping
    public GetDocumentResponse updateDocument(@RequestBody UpdateDocumentRequest request, @PathVariable Long id) {
        return service.updateDocument(request, id);
    }
    @DeleteMapping("{id}")
    public void deleteDocument(@PathVariable Long id) {
        service.deleteDocument(id);
    }
}
