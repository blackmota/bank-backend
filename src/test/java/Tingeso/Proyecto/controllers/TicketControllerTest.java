package Tingeso.Proyecto.controllers;

import Tingeso.Proyecto.entities.TicketEntity;
import Tingeso.Proyecto.services.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TicketControllerTest {

    @InjectMocks
    private TicketController ticketController;

    @Mock
    private TicketService ticketService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveTicket() {
        //Given
        TicketEntity ticket = new TicketEntity();
        when(ticketService.addTicket(ticket)).thenReturn(ticket);

        //When
        ResponseEntity<TicketEntity> response = ticketController.saveTicket(ticket);

        //Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(ticket);
    }

    @Test
    public void testGetTicketById() {
        //Given
        Long id = 1L;
        TicketEntity ticket = new TicketEntity();
        when(ticketService.getById(id)).thenReturn(ticket);

        //When
        ResponseEntity<TicketEntity> response = ticketController.getTicketById(id);

        //Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(ticket);
    }

    @Test
    public void testCancelTicket() {
        //Given
        TicketEntity ticket = new TicketEntity();
        TicketEntity canceledTicket = new TicketEntity();
        when(ticketService.cancelTicket(ticket)).thenReturn(canceledTicket);
        when(ticketService.save(canceledTicket)).thenReturn(canceledTicket);

        //When
        ResponseEntity<TicketEntity> response = ticketController.cancelTicket(ticket);

        //Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(canceledTicket);
    }

    @Test
    public void testGetByUser() {
        //Given
        Integer userId = 1;
        List<TicketEntity> ticketList = new ArrayList<>();
        when(ticketService.getByIdUser(userId)).thenReturn(ticketList);

        //When
        ResponseEntity<List<TicketEntity>> response = ticketController.getByUser(userId);

        //Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(ticketList);
    }

    @Test
    public void testValidateR1() {
        //Given
        int income = 10000;
        float fee = 300.5f;
        when(ticketService.Validater1(income, fee)).thenReturn(true);

        //When
        ResponseEntity<Boolean> response = ticketController.validateR1(income, fee);

        //Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isTrue();
    }

    @Test
    public void testValidateR6() {
        //Given
        LocalDate birth = LocalDate.of(1990, 1, 1);
        int years = 34;
        when(ticketService.validate_r6(birth, years)).thenReturn(true);

        //When
        ResponseEntity<Boolean> response = ticketController.validateR6(birth, years);

        //Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isTrue();
    }

    @Test
    public void testStepTicket() {
        //Given
        TicketEntity ticket = new TicketEntity();
        ticket.setStep(1);
        when(ticketService.save(ticket)).thenReturn(ticket);

        //When
        ResponseEntity<TicketEntity> response = ticketController.stepTicket(ticket);

        //Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getStep()).isEqualTo(2);  // Verificamos que el paso haya incrementado en 1
    }

    @Test
    public void testValidateR7() {
        //Given
        boolean r1 = true, r2 = true, r3 = false, r4 = true, r5 = true;
        int expectedResult = 4;
        when(ticketService.validate_r7(r1, r2, r3, r4, r5)).thenReturn(expectedResult);

        //When
        ResponseEntity<Integer> response = ticketController.validateR7(r1, r2, r3, r4, r5);

        //Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(expectedResult);
    }

    @Test
    public void testApproveTicket() {
        //Given
        TicketEntity ticket = new TicketEntity();
        TicketEntity approvedTicket = new TicketEntity();
        when(ticketService.approveTicket(ticket)).thenReturn(approvedTicket);
        when(ticketService.save(approvedTicket)).thenReturn(approvedTicket);

        //When
        ResponseEntity<TicketEntity> response = ticketController.approveTicket(ticket);

        //Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(approvedTicket);
    }

    @Test
    public void testAcceptUserTicket() {
        //Given
        TicketEntity ticket = new TicketEntity();
        TicketEntity acceptedTicket = new TicketEntity();
        when(ticketService.acceptLoanUser(ticket)).thenReturn(acceptedTicket);

        //When
        ResponseEntity<TicketEntity> response = ticketController.acceptUserTicket(ticket);

        //Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(acceptedTicket);
    }

    @Test
    public void testAproveTicket() {
        //Given
        TicketEntity ticket = new TicketEntity();
        TicketEntity approvedTicket = new TicketEntity();
        when(ticketService.approveTicket(ticket)).thenReturn(approvedTicket);

        //When
        ResponseEntity<TicketEntity> response = ticketController.aproveTicket(ticket);

        //Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(approvedTicket);
    }

    @Test
    public void testGetByStatus() {
        //Given
        List<TicketEntity> ticketList = new ArrayList<>();
        when(ticketService.getAllByStatus()).thenReturn(ticketList);

        //When
        ResponseEntity<List<TicketEntity>> response = ticketController.getByStatus();

        //Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(ticketList);
    }

    @Test
    public void testValidateR3() {
        //Given
        Integer seniority = 10;
        boolean independant = true;
        when(ticketService.Validate_r3(seniority, independant)).thenReturn(true);

        //When
        ResponseEntity<Boolean> response = ticketController.validateR3(seniority, independant);

        //Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isTrue();
    }

    @Test
    public void testValidateR4() {
        //Given
        float fee = 150.75f;
        float debt = 500.00f;
        float income = 1200.00f;
        when(ticketService.Validate_r4(fee, debt, income)).thenReturn(true);

        //When
        ResponseEntity<Boolean> response = ticketController.validateR4(fee, debt, income);

        //Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isTrue();
    }

    @Test
    public void testValidateR5() {
        //Given
        float amount = 1000.00f;
        Integer type = 1;
        float valuation = 1200.00f;
        when(ticketService.Validate_r5(amount, type, valuation)).thenReturn(true);

        //When
        ResponseEntity<Boolean> response = ticketController.validateR5(amount, type, valuation);

        //Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isTrue();
    }

    @Test
    public void testRejectTicket() {
        //Given
        TicketEntity ticket = new TicketEntity();
        TicketEntity rejectedTicket = new TicketEntity();
        when(ticketService.rejectTicket(ticket)).thenReturn(rejectedTicket);
        when(ticketService.save(rejectedTicket)).thenReturn(rejectedTicket);

        //When
        ResponseEntity<TicketEntity> response = ticketController.rejectTicket(ticket);

        //Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(rejectedTicket);

        verify(ticketService, times(1)).rejectTicket(ticket);
        verify(ticketService, times(1)).save(rejectedTicket);
    }

    @Test
    public void testAcceptTicketExecutive() {
        //Given
        TicketEntity ticket = new TicketEntity();
        TicketEntity acceptedTicket = new TicketEntity();
        when(ticketService.acceptTicketExecutive(ticket)).thenReturn(acceptedTicket);
        when(ticketService.save(acceptedTicket)).thenReturn(acceptedTicket);

        //When
        ResponseEntity<TicketEntity> response = ticketController.acceptTicketExecutive(ticket);

        //Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(acceptedTicket);

        verify(ticketService, times(1)).acceptTicketExecutive(ticket);
        verify(ticketService, times(1)).save(acceptedTicket);
    }

    @Test
    public void testSaveTicketOld() {
        //Given
        TicketEntity ticket = new TicketEntity();
        TicketEntity savedTicket = new TicketEntity();
        when(ticketService.save(ticket)).thenReturn(savedTicket);

        //When
        ResponseEntity<TicketEntity> response = ticketController.saveTicketOld(ticket);

        //Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(savedTicket);

        verify(ticketService, times(1)).save(ticket);
    }
}
