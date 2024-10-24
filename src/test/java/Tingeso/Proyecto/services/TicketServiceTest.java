package Tingeso.Proyecto.services;

import Tingeso.Proyecto.entities.TicketEntity;
import Tingeso.Proyecto.repositories.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class TicketServiceTest {


    @InjectMocks
    private TicketService ticketService; // Cambia esto por el nombre de tu clase de servicio

    @Mock
    private TicketRepository ticketRepo; // Mock de tu repositorio

    @Mock
    private LoanServices loanServices; // Mock del servicio de cálculo de préstamos

    @BeforeEach
    public void setup() {
        openMocks(this);
    }

    TicketEntity ticket = new TicketEntity();

    @Test
    public void testAddTicket() {
        // Datos de prueba
        TicketEntity ticket = new TicketEntity();
        ticket.setAmount(10000);
        ticket.setInterest(5.0f);
        ticket.setYears(2);

        // Valor esperado de la cuota
        float expectedFee = 500.0f; // Cambia esto al valor que esperas que retorne calculateLoan

        // Configurar el comportamiento del mock
        when(loanServices.calculateLoan(ticket.getAmount(), ticket.getInterest(), ticket.getYears())).thenReturn(expectedFee);
        when(ticketRepo.save(ticket)).thenReturn(ticket);

        // Llamar al método a probar
        TicketEntity savedTicket = ticketService.addTicket(ticket);

        // Verificaciones
        assertThat( savedTicket.getStatus()).isEqualTo("E3");
        assertThat(savedTicket.getStep()).isEqualTo(1);
        assertThat(savedTicket.getR1()).isFalse();
        assertThat(savedTicket.getFee()).isEqualTo(expectedFee);

        // Verifica que el repositorio haya sido llamado
        // verify(ticketRepo).save(ticket);
    }


    @Test
    void whenCancelTicket_thenNoExceptions() {
        //Given
        String status = "E8";

        //when
        TicketEntity ticketchanged = ticketService.cancelTicket(ticket);

        //Then
        assertThat(ticketchanged.getStatus()).isEqualTo(status);
    }

    @Test
    void whenAcceptTicket_thenTicketAccepted() {
        //Given
        String status = "E5";

        //When
        TicketEntity ticketchanged = ticketService.acceptLoanUser(ticket);

        //Then
        assertThat(ticketchanged.getStatus()).isEqualTo(status);
    }

    @Test
    void whenApproveTicket_thenTicketApproved() {
        //Given
        String status = "E6";

        //When
        TicketEntity ticketChanged = ticketService.approveTicket(ticket);

        //Then
        assertThat(ticketChanged.getStatus()).isEqualTo(status);
    }

    @Test
    void whenValidateR1_thenTrue() {
        //Given
        int income = 1000000;
        float fee = 349000;

        //When
        Boolean result = ticketService.Validater1(income,fee);

        //Then
        assertThat(result).isTrue();
    }

    @Test
    void whenValidateR1_thenFalse() {
        //Given
        int income = 1000000;
        float fee = 359000;

        //When
        Boolean result = ticketService.Validater1(income,fee);

        //Then
        assertThat(result).isFalse();
    }

    @Test
    void whenValidateR3_thenTrue(){
        //Given
        Boolean independent = true;
        int seniority = 2;

        //When
        Boolean result = ticketService.Validate_r3(seniority,independent);

        //Then
        assertThat(result).isTrue();
    }

    @Test
    void whenValidateR3_thenFalse(){
        //Given
        Boolean independent = true;
        int seniority = 1;

        //When
        Boolean result = ticketService.Validate_r3(seniority,independent);

        //Then
        assertThat(result).isFalse();
    }

    @Test
    void whenValidateR3_thenTrue2(){
        //Given
        Boolean independent = false;
        int seniority = 2;

        //When
        Boolean result = ticketService.Validate_r3(seniority,independent);

        //Then
        assertThat(result).isTrue();
    }

    @Test
    void whenValidateR3_thenFalse2(){
        //Given
        Boolean independent = false;
        int seniority = 0;

        //When
        Boolean result = ticketService.Validate_r3(seniority,independent);

        //Then
        assertThat(result).isFalse();
    }

    @Test
    void whenValidateR4_thenTrue(){
        //Given
        float fee = 450000f;
        float debt = 250000f;
        float income = 1500000f;

        //When
        Boolean result = ticketService.Validate_r4(fee,debt,income);

        //Then
        assertThat(result).isTrue();

    }

    @Test
    void whenValidateR4_thenFalse(){
        //Given
        float fee = 450000f;
        float debt = 250000f;
        float income = 1300000f;

        //When
        Boolean result = ticketService.Validate_r4(fee,debt,income);

        //Then
        assertThat(result).isFalse();

    }

    @Test
    void whenValidateR5_thenTrue(){
        float loanAmount = 150000000f;
        Integer loanType = 1;
        float propertyValuation = 200000000f;

        //When
        Boolean result = ticketService.Validate_r5(loanAmount,loanType,propertyValuation);

        //Then
        assertThat(result).isTrue();
    }

    @Test
    void whenValidateR5_thenFalse(){
        float loanAmount = 150000000f;
        Integer loanType = 1;
        float propertyValuation = 180000000f;

        //When
        Boolean result = ticketService.Validate_r5(loanAmount,loanType,propertyValuation);

        //Then
        assertThat(result).isFalse();
    }

    @Test
    void whenValidateR5_thenFalse2(){
        float loanAmount = 150000000f;
        Integer loanType = 2;
        float propertyValuation = 180000000f;

        //When
        Boolean result = ticketService.Validate_r5(loanAmount,loanType,propertyValuation);

        //Then
        assertThat(result).isFalse();
    }

    @Test
    void whenValidateR5_thenTrue2(){
        float loanAmount = 120000000f;
        Integer loanType = 2;
        float propertyValuation = 180000000f;

        //When
        Boolean result = ticketService.Validate_r5(loanAmount,loanType,propertyValuation);

        //Then
        assertThat(result).isTrue();
    }

    @Test
    void whenValidateR5_thenTrue3(){
        float loanAmount = 10000000f;
        Integer loanType = 3;
        float propertyValuation = 180000000f;

        //When
        Boolean result = ticketService.Validate_r5(loanAmount,loanType,propertyValuation);

        //Then
        assertThat(result).isTrue();
    }

    @Test
    void whenValidateR5_thenFalse3(){
        float loanAmount = 120000000f;
        Integer loanType = 3;
        float propertyValuation = 180000000f;

        //When
        Boolean result = ticketService.Validate_r5(loanAmount,loanType,propertyValuation);

        //Then
        assertThat(result).isFalse();
    }

    @Test
    void whenValidateR5_thenTrue4(){
        float loanAmount = 90000000f;
        Integer loanType = 2;
        float propertyValuation = 180000000f;

        //When
        Boolean result = ticketService.Validate_r5(loanAmount,loanType,propertyValuation);

        //Then
        assertThat(result).isTrue();
    }

    @Test
    void whenValidateR5_thenFalse4(){
        float loanAmount = 120000000f;
        Integer loanType = 4;
        float propertyValuation = 180000000f;

        //When
        Boolean result = ticketService.Validate_r5(loanAmount,loanType,propertyValuation);

        //Then
        assertThat(result).isFalse();
    }

    @Test
    void whenValidateR6_thenTrue(){
        //Given
        LocalDate birthDate = LocalDate.of(2015,10,03);
        Integer Years = 30;

        //When
        Boolean result = ticketService.validate_r6(birthDate,Years);

        //Then
        assertThat(result).isTrue();
    }

    @Test
    void whenValidateR6_thenFalse(){
        //Given
        LocalDate birthDate = LocalDate.of(1965,10,03);
        Integer Years = 30;

        //When
        Boolean result = ticketService.validate_r6(birthDate,Years);

        //Then
        assertThat(result).isFalse();
    }

    @Test
    void whenValidateR6_thenCorrect(){
        //Given
        Boolean r1 = false;
        Boolean r2 = false;
        Boolean r3 = false;
        Boolean r4 = false;
        Boolean r5 = false;

        //When
        Integer result = ticketService.validate_r7(r1,r2,r3,r4,r5);

        //Then
        assertThat(result).isEqualTo(0);
    }

    @Test
    void whenValidateR6_thenCorrect2(){
        //Given
        Boolean r1 = true;
        Boolean r2 = true;
        Boolean r3 = true;
        Boolean r4 = false;
        Boolean r5 = false;

        //When
        Integer result = ticketService.validate_r7(r1,r2,r3,r4,r5);

        //Then
        assertThat(result).isEqualTo(2);
    }

    @Test
    void whenValidateR6_thenCorrect3(){
        //Given
        Boolean r1 = true;
        Boolean r2 = true;
        Boolean r3 = true;
        Boolean r4 = true;
        Boolean r5 = true;

        //When
        Integer result = ticketService.validate_r7(r1,r2,r3,r4,r5);

        //Then
        assertThat(result).isEqualTo(1);
    }



}
