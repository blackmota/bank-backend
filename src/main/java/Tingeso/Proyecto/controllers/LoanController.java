package Tingeso.Proyecto.controllers;

import Tingeso.Proyecto.services.LoanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/loan")
@CrossOrigin("*")
public class LoanController {

    @Autowired
    private LoanServices loanServ;

    @GetMapping("/calculate/{Amount}/{Rate}/{Time}")
    public ResponseEntity<Float> calculateLoan(@PathVariable float Amount, @PathVariable float Rate, @PathVariable float Time){
        float Loan = loanServ.calculateLoan(Amount,Rate,Time);
        return ResponseEntity.ok(Loan);
    }

    @GetMapping("/calculateTotal/{loan}/{interest}/{years}")
    public ResponseEntity<Float> calculateTotalLoan(@PathVariable Float loan, @PathVariable Float interest, @PathVariable Float years){
        Float total = loanServ.calculateTotalLoanAmount(loan,interest,years,0.03f,20000f,1f);
        return ResponseEntity.ok(total);
    }
}
