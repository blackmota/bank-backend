package Tingeso.Proyecto.services;

import Tingeso.Proyecto.entities.DocumentEntity;
import Tingeso.Proyecto.repositories.DocumentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepo;

    public DocumentEntity saveDocument(Integer User, Integer Ticket,Integer type, MultipartFile file) throws IOException{
        DocumentEntity document = new DocumentEntity();
        document.setType(type);
        document.setUserid(User);
        document.setTicketid(Ticket);
        document.setFile(file.getBytes());

        return documentRepo.save(document);

    }

    public DocumentEntity getDocumentById(Long id){
        return documentRepo.findById(id).get();
    }

    @Transactional
    public DocumentEntity getByticketAndType(Integer ticketId, Integer type){
        return documentRepo.findByTicketidAndType(ticketId, type);
    }

    @Transactional
    public List<DocumentEntity> getAllByTicket(Integer ticketId){
        return documentRepo.findAllByTicketid(ticketId);
    }

    public ByteArrayOutputStream createZipFromDocuments(List<DocumentEntity> documents) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ZipOutputStream zos = new ZipOutputStream(baos)) {
            for (DocumentEntity document : documents) {
                ZipEntry zipEntry = new ZipEntry("document_" + document.getTicketid() + "_" + documentType(document.getType()).trim() + ".pdf");
                zos.putNextEntry(zipEntry);
                zos.write(document.getFile());
                zos.closeEntry();
            }
        }
        return baos;
    }

    public String documentType(Integer type){
        switch (type){
            case 1:
                return "Ingresos";
            case 2:
                return "C_Aval";
            case 3:
                return "Credit_History";
            case 4:
                return "First_property";
            case 5:
                return "Finantial_status";
            case 6:
                return "bussines_plan";
            case 7:
                return "remodelation_budget";
            case 8:
                return "C_Aval_Act";
        }
        return null;
    }

}
