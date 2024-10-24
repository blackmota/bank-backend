package Tingeso.Proyecto.services;

import Tingeso.Proyecto.entities.DocumentEntity;
import Tingeso.Proyecto.repositories.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DocumentServiceTest {

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private MultipartFile mockFile;

    @InjectMocks
    private DocumentService documentService;

    @Test
    void whenSaveDocument_thenDocumentSaved() throws IOException {
        // Given
        DocumentEntity document = new DocumentEntity();
        document.setType(1);
        document.setUserid(1);
        document.setTicketid(1);
        byte[] fileContent = {1, 2, 3};
        document.setFile(fileContent);
        when(mockFile.getBytes()).thenReturn(fileContent);

        when(documentRepository.save(Mockito.any(DocumentEntity.class))).thenReturn(document);

        // When
        DocumentEntity result = documentService.saveDocument(1, 1, 1, mockFile);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getUserid()).isEqualTo(1L);
        assertThat(result.getTicketid()).isEqualTo(1L);
        assertThat(result.getFile()).isEqualTo(fileContent);
    }

    @Test
    void whenGetDocumentById_thenReturnDocument() {
        // Given
        DocumentEntity document = new DocumentEntity();
        document.setId(1L);
        document.setUserid(1);
        document.setTicketid(1);

        when(documentRepository.findById(1L)).thenReturn(Optional.of(document));

        // When
        DocumentEntity result = documentService.getDocumentById(1L);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void whenGetByTicketAndType_thenReturnDocument() {
        // Given
        DocumentEntity document = new DocumentEntity();
        document.setTicketid(1);
        document.setType(1);

        when(documentRepository.findByTicketidAndType(1, 1)).thenReturn(document);

        // When
        DocumentEntity result = documentService.getByticketAndType(1, 1);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTicketid()).isEqualTo(1);
        assertThat(result.getType()).isEqualTo(1);
    }

    @Test
    void whenGetAllByTicket_thenReturnDocumentList() {
        // Given
        DocumentEntity document1 = new DocumentEntity();
        document1.setTicketid(1);

        DocumentEntity document2 = new DocumentEntity();
        document2.setTicketid(1);

        List<DocumentEntity> documents = Arrays.asList(document1, document2);

        when(documentRepository.findAllByTicketid(1)).thenReturn(documents);

        // When
        List<DocumentEntity> result = documentService.getAllByTicket(1);

        // Then
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void whenCreateZipFromDocuments_thenZipCreated() throws IOException {
        // Given
        DocumentEntity document1 = new DocumentEntity();
        document1.setTicketid(1);
        document1.setType(1);
        document1.setFile(new byte[]{1, 2, 3});

        DocumentEntity document2 = new DocumentEntity();
        document2.setTicketid(1);
        document2.setType(2);
        document2.setFile(new byte[]{4, 5, 6});

        List<DocumentEntity> documents = Arrays.asList(document1, document2);

        // When
        ByteArrayOutputStream zipOutput = documentService.createZipFromDocuments(documents);

        // Then
        assertThat(zipOutput).isNotNull();
        assertThat(zipOutput.size()).isGreaterThan(0);  // Comprobamos que el tamaño no sea cero
    }

    @Test
    void whenDocumentType_thenReturnCorrectType() {
        // When
        String result1 = documentService.documentType(1);
        String result2 = documentService.documentType(2);
        String result3 = documentService.documentType(3);
        String result4 = documentService.documentType(8);

        // Then
        assertThat(result1).isEqualTo("Ingresos");
        assertThat(result2).isEqualTo("C_Aval");
        assertThat(result3).isEqualTo("Credit_History");
        assertThat(result4).isEqualTo("C_Aval_Act");
    }
}
