package Tingeso.Proyecto.controllers;

import Tingeso.Proyecto.entities.DocumentEntity;
import Tingeso.Proyecto.services.DocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class DocumentControllerTest {

    @InjectMocks
    private DocumentController documentController;

    @Mock
    private DocumentService documentServ;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUploadDocument_Success() throws IOException {
        MockMultipartFile file = new MockMultipartFile("File", "document.pdf", "application/pdf", "sample content".getBytes());
        DocumentEntity document = new DocumentEntity();
        when(documentServ.saveDocument(1, 2, 3, file)).thenReturn(document);

        ResponseEntity<DocumentEntity> response = documentController.uploadDocument(1, 2, 3, file);

        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody()).isEqualTo(document);
        verify(documentServ, times(1)).saveDocument(1, 2, 3, file);
    }

    @Test
    public void testUploadDocument_Failure() throws IOException {
        MockMultipartFile file = new MockMultipartFile("File", "document.pdf", "application/pdf", "sample content".getBytes());
        when(documentServ.saveDocument(1, 2, 3, file)).thenThrow(new IOException());

        ResponseEntity<DocumentEntity> response = documentController.uploadDocument(1, 2, 3, file);

        assertThat(response.getStatusCodeValue()).isEqualTo(500);
        assertThat(response.getBody()).isNull();
        verify(documentServ, times(1)).saveDocument(1, 2, 3, file);
    }

    @Test
    public void testObtainDocument_Success() {
        DocumentEntity document = new DocumentEntity();
        document.setFile("sample content".getBytes());
        when(documentServ.getDocumentById(1L)).thenReturn(document);

        ResponseEntity<byte[]> response = documentController.obtainDocument(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("sample content".getBytes());
        verify(documentServ, times(1)).getDocumentById(1L);
    }

    @Test
    public void testObtainDocumentByTicketAndType_Success() {
        DocumentEntity document = new DocumentEntity();
        document.setFile("sample content".getBytes());
        when(documentServ.getByticketAndType(1, 2)).thenReturn(document);

        ResponseEntity<byte[]> response = documentController.obtainDocumentByticketAndType(1, 2);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("sample content".getBytes());
        verify(documentServ, times(1)).getByticketAndType(1, 2);
    }

    @Test
    public void testObtainDocumentByTicket_Success() throws IOException {
        List<DocumentEntity> documents = new ArrayList<>();
        documents.add(new DocumentEntity());
        ByteArrayOutputStream zipStream = new ByteArrayOutputStream();
        zipStream.write("zip content".getBytes());
        when(documentServ.getAllByTicket(1)).thenReturn(documents);
        when(documentServ.createZipFromDocuments(documents)).thenReturn(zipStream);

        ResponseEntity<byte[]> response = documentController.obtainDocumentByticket(1);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("zip content".getBytes());
        verify(documentServ, times(1)).getAllByTicket(1);
        verify(documentServ, times(1)).createZipFromDocuments(documents);
    }
}
