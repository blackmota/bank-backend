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
        TicketEntity ticket = new TicketEntity();
        when(ticketService.addTicket(ticket)).thenReturn(ticket);

        ResponseEntity<TicketEntity> response = ticketController.saveTicket(ticket);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(ticket);
    }

    @Test
    public void testGetTicketById() {
        Long id = 1L;
        TicketEntity ticket = new TicketEntity();
        when(ticketService.getById(id)).thenReturn(ticket);

        ResponseEntity<TicketEntity> response = ticketController.getTicketById(id);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(ticket);
    }

    @Test
    public void testCancelTicket() {
        TicketEntity ticket = new TicketEntity();
        TicketEntity canceledTicket = new TicketEntity();
        when(ticketService.cancelTicket(ticket)).thenReturn(canceledTicket);
        when(ticketService.save(canceledTicket)).thenReturn(canceledTicket);

        ResponseEntity<TicketEntity> response = ticketController.cancelTicket(ticket);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(canceledTicket);
    }

    @Test
    public void testGetByUser() {
        Integer userId = 1;
        List<TicketEntity> ticketList = new ArrayList<>();
        when(ticketService.getByIdUser(userId)).thenReturn(ticketList);

        ResponseEntity<List<TicketEntity>> response = ticketController.getByUser(userId);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(ticketList);
    }

    @Test
    public void testValidateR1() {
        int income = 10000;
        float fee = 300.5f;
        when(ticketService.Validater1(income, fee)).thenReturn(true);

        ResponseEntity<Boolean> response = ticketController.validateR1(income, fee);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isTrue();
    }

    @Test
    public void testValidateR6() {
        LocalDate birth = LocalDate.of(1990, 1, 1);
        int years = 34;
        when(ticketService.validate_r6(birth, years)).thenReturn(true);

        ResponseEntity<Boolean> response = ticketController.validateR6(birth, years);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isTrue();
    }

    @Test
    public void testStepTicket() {
        TicketEntity ticket = new TicketEntity();
        ticket.setStep(1);
        when(ticketService.save(ticket)).thenReturn(ticket);

        ResponseEntity<TicketEntity> response = ticketController.stepTicket(ticket);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getStep()).isEqualTo(2);  // Verificamos que el paso haya incrementado en 1
    }

    @Test
    public void testValidateR7() {
        boolean r1 = true, r2 = true, r3 = false, r4 = true, r5 = true;
        int expectedResult = 4;
        when(ticketService.validate_r7(r1, r2, r3, r4, r5)).thenReturn(expectedResult);

        ResponseEntity<Integer> response = ticketController.validateR7(r1, r2, r3, r4, r5);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(expectedResult);
    }

    @Test
    public void testApproveTicket() {
        TicketEntity ticket = new TicketEntity();
        TicketEntity approvedTicket = new TicketEntity();
        when(ticketService.approveTicket(ticket)).thenReturn(approvedTicket);
        when(ticketService.save(approvedTicket)).thenReturn(approvedTicket);

        ResponseEntity<TicketEntity> response = ticketController.approveTicket(ticket);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(approvedTicket);
    }

    @Test
    public void testAcceptUserTicket() {
        TicketEntity ticket = new TicketEntity();
        TicketEntity acceptedTicket = new TicketEntity();
        when(ticketService.acceptLoanUser(ticket)).thenReturn(acceptedTicket);

        ResponseEntity<TicketEntity> response = ticketController.acceptUserTicket(ticket);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(acceptedTicket);
    }

    @Test
    public void testAproveTicket() {
        TicketEntity ticket = new TicketEntity();
        TicketEntity approvedTicket = new TicketEntity();
        when(ticketService.approveTicket(ticket)).thenReturn(approvedTicket);

        ResponseEntity<TicketEntity> response = ticketController.aproveTicket(ticket);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(approvedTicket);
    }

    @Test
    public void testGetByStatus() {
        List<TicketEntity> ticketList = new ArrayList<>();
        when(ticketService.getAllByStatus()).thenReturn(ticketList);

        ResponseEntity<List<TicketEntity>> response = ticketController.getByStatus();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(ticketList);
    }

    @Test
    public void testValidateR3() {
        Integer seniority = 10;
        boolean independant = true;
        when(ticketService.Validate_r3(seniority, independant)).thenReturn(true);

        ResponseEntity<Boolean> response = ticketController.validateR3(seniority, independant);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isTrue();
    }

    @Test
    public void testValidateR4() {
        float fee = 150.75f;
        float debt = 500.00f;
        float income = 1200.00f;
        when(ticketService.Validate_r4(fee, debt, income)).thenReturn(true);

        ResponseEntity<Boolean> response = ticketController.validateR4(fee, debt, income);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isTrue();
    }

    @Test
    public void testValidateR5() {
        float amount = 1000.00f;
        Integer type = 1;
        float valuation = 1200.00f;
        when(ticketService.Validate_r5(amount, type, valuation)).thenReturn(true);

        ResponseEntity<Boolean> response = ticketController.validateR5(amount, type, valuation);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isTrue();
    }

    @Test
    public void testRejectTicket() {
        TicketEntity ticket = new TicketEntity();
        TicketEntity rejectedTicket = new TicketEntity();
        when(ticketService.rejectTicket(ticket)).thenReturn(rejectedTicket);
        when(ticketService.save(rejectedTicket)).thenReturn(rejectedTicket);

        ResponseEntity<TicketEntity> response = ticketController.rejectTicket(ticket);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(rejectedTicket);

        verify(ticketService, times(1)).rejectTicket(ticket);
        verify(ticketService, times(1)).save(rejectedTicket);
    }

    @Test
    public void testAcceptTicketExecutive() {
        TicketEntity ticket = new TicketEntity();
        TicketEntity acceptedTicket = new TicketEntity();
        when(ticketService.acceptTicketExecutive(ticket)).thenReturn(acceptedTicket);
        when(ticketService.save(acceptedTicket)).thenReturn(acceptedTicket);

        ResponseEntity<TicketEntity> response = ticketController.acceptTicketExecutive(ticket);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(acceptedTicket);

        verify(ticketService, times(1)).acceptTicketExecutive(ticket);
        verify(ticketService, times(1)).save(acceptedTicket);
    }

    @Test
    public void testSaveTicketOld() {
        TicketEntity ticket = new TicketEntity();
        TicketEntity savedTicket = new TicketEntity();
        when(ticketService.save(ticket)).thenReturn(savedTicket);

        ResponseEntity<TicketEntity> response = ticketController.saveTicketOld(ticket);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(savedTicket);

        verify(ticketService, times(1)).save(ticket);
    }
}
