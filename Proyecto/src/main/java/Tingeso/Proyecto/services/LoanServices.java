package Tingeso.Proyecto.services;

import org.springframework.stereotype.Service;

@Service
public class LoanServices {

    public float calculateLoan(float loanAmount, float annualInterestRate, float loanTimeYears) {
        float monthlyInterestRate = annualInterestRate / 1200;
        float loanTimeMonths = loanTimeYears*12;
        float monthlyPayment = (float) (loanAmount*monthlyInterestRate* Math.pow(1 + monthlyInterestRate, loanTimeMonths));
        monthlyPayment = (float) (monthlyPayment/(Math.pow(1+monthlyInterestRate,loanTimeMonths)-1));
        return monthlyPayment;
    }

    public float calculateTotalLoanAmount(float loanAmount, float annualInterestRate,
                                          float loanTimeYears, float desgravamenInsurance,
                                          float fireInsurance, float administrationFee) {
        float monthlyPayment = calculateLoan(loanAmount, annualInterestRate, loanTimeYears);
        float desgravamenInsuranceMonthly = loanAmount*desgravamenInsurance/100;
        float administrationFeeAmount = administrationFee*loanAmount/100;
        float monthlyAmount = monthlyPayment+desgravamenInsuranceMonthly+fireInsurance;
        return monthlyAmount*loanTimeYears*12+administrationFeeAmount;
    }
}
