package Tingeso.Proyecto.controllers;

import Tingeso.Proyecto.entities.DocumentEntity;
import Tingeso.Proyecto.repositories.DocumentRepository;
import Tingeso.Proyecto.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/document")
@CrossOrigin("*")
public class DocumentController {
    @Autowired
    private DocumentService documentServ;

    @PostMapping("/upload")
    public ResponseEntity<DocumentEntity> uploadDocument(@RequestParam("User") Integer User,
                                                         @RequestParam("Ticket") Integer Ticket,
                                                         @RequestParam("Type") Integer Type,
                                                         @RequestParam("File") MultipartFile file) {
        try {
            DocumentEntity documento = documentServ.saveDocument( User, Ticket,Type, file);
            return new ResponseEntity<>(documento, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // Endpoint para obtener un documento por ID
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> obtainDocument(@PathVariable Long id) {
        DocumentEntity documento = documentServ.getDocumentById(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"documento_" + id + ".pdf\"")
                .body(documento.getFile());

    }

    @GetMapping("/{ticket}/{type}/")
    public ResponseEntity<byte[]> obtainDocumentByticketAndType(@PathVariable Integer ticket, @PathVariable Integer type) {
        DocumentEntity documento = documentServ.getByticketAndType(ticket,type);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"documento_" +  ticket + type + ".pdf\"")
                .body(documento.getFile());

    }

    @GetMapping("/all/{ticketId}")
    public ResponseEntity<byte[]> obtainDocumentByticket(@PathVariable Integer ticketId) throws IOException {
        List<DocumentEntity> documents = documentServ.getAllByTicket(ticketId);

        ByteArrayOutputStream zipStream = documentServ.createZipFromDocuments(documents);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=documents_" + ticketId + ".zip")
                .body(zipStream.toByteArray());
    }



}
