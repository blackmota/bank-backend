package Tingeso.Proyecto.controllers;

import Tingeso.Proyecto.services.LoanServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class LoanControllerTest {

    @InjectMocks
    LoanController loanController;

    @Mock
    LoanServices loanServices;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateLoan() {
        // Given
        float amount = 10000f;
        float rate = 5.5f;
        float time = 10f;
        float expectedLoan = 12000f;


        when(loanServices.calculateLoan(amount, rate, time)).thenReturn(expectedLoan);

        // When
        ResponseEntity<Float> response = loanController.calculateLoan(amount, rate, time);

        // Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(expectedLoan);
    }

    @Test
    public void testCalculateTotalLoan() {
        // Given
        float loan = 15000f;
        float interest = 0.05f;
        float years = 5f;
        float expectedTotal = 17500f;

        when(loanServices.calculateTotalLoanAmount(loan, interest, years, 0.03f, 20000f, 1f)).thenReturn(expectedTotal);

        // When
        ResponseEntity<Float> response = loanController.calculateTotalLoan(loan, interest, years);

        // Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(expectedTotal);
    }
}
