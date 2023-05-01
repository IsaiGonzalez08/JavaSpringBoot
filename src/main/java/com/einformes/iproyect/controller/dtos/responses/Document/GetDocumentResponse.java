package com.einformes.iproyect.controller.dtos.responses.Document;

public class GetDocumentResponse {
    private Long id_document;
    private String document;

    public Long getId_document() {
        return id_document;
    }

    public void setId_document(Long id_document) {
        this.id_document = id_document;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
