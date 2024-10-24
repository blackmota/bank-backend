package Tingeso.Proyecto.services;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoanServiceTest {
    LoanServices loanserv = new LoanServices();

    @Test
    void whenCalculateLoan_thenCorrect() {

        //Given
        float Amount = 100000000;
        float AnnualInterestRate = 4.5f;
        float years = 20;

        //when
        float monthlyPayment = loanserv.calculateLoan(Amount,AnnualInterestRate,years);

        //Then
        assertThat(monthlyPayment).isEqualTo(632652.9f);
    }

    @Test
    void whenCalculateTotalLoanAmount_thenCorrect() {

        //Given
        float Amount = 100000000;
        float AnnualInterestRate = 4.5f;
        float years = 20;
        float desgravamenInsurance = 0.03f;
        float fireInsurance = 20000;
        float administrationFee = 1;

        //When
        float totalAmount = loanserv.calculateTotalLoanAmount(Amount, AnnualInterestRate,years,desgravamenInsurance,fireInsurance,administrationFee);

        //Then
        assertThat(totalAmount).isEqualTo(164836704.9f);
    }
}

