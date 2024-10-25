package Tingeso.Proyecto.services;

import Tingeso.Proyecto.entities.TicketEntity;
import Tingeso.Proyecto.entities.UserEntity;
import Tingeso.Proyecto.services.LoanServices;
import Tingeso.Proyecto.repositories.TicketRepository;
import Tingeso.Proyecto.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private LoanServices loanServices;

    public List<TicketEntity> getByIdUser(Integer id_user){
        return ticketRepo.getAllByUsuario( id_user);
    }

    public TicketEntity save(TicketEntity ticket){
        return ticketRepo.save(ticket);
    }

    public TicketEntity addTicket(TicketEntity ticket) {
        ticket.setStatus("E3");
        ticket.setStep(1);
        ticket.setFee(loanServices.calculateLoan(ticket.getAmount(),ticket.getInterest(),ticket.getYears()));
        return ticketRepo.save(ticket);
    }

    public List<TicketEntity> getAllByStatus(){
        return ticketRepo.getAllByStatusNotInOrderByStepDesc(Arrays.asList("E8", "E4", "E6","E7"));
    }

    public TicketEntity getById(Long id){
        return ticketRepo.findById(id).get();
    }

    public TicketEntity rejectTicket(TicketEntity ticket){
        ticket.setStatus("E7");
        ticket.setStep(1);
        return ticket;
    }

    public TicketEntity acceptTicketExecutive(TicketEntity ticket){
        ticket.setStatus("E4");
        ticket.setStep(ticket.getStep()+1);
        return ticket;
    }

    public TicketEntity cancelTicket(TicketEntity ticket) {
        ticket.setStatus("E8");
        ticket.setStep(1);
        return ticket;
    }

    public TicketEntity acceptLoanUser(TicketEntity ticket) {
        ticket.setStatus("E5");
        return ticket;
    }

    public TicketEntity approveTicket(TicketEntity ticket) {
        ticket.setStatus("E6");
        return ticket;
    }

    public Boolean Validater1(int income, float fee){
        float percentage = fee/income;
        return !(percentage > 0.35f);
    }


    public Boolean Validate_r3(int seniority, boolean Independent){
        if(Independent){
            return seniority >= 2;
        }else{
            return seniority >= 1;
        }
    }

    public Boolean Validate_r4(float fee, float debt, float income){
        float totaldebt = fee + debt;
        return totaldebt <= income/2;
    }

    public Boolean Validate_r5(float loanAmount, Integer LoanType, float propertyValuation){
        float index = loanAmount / propertyValuation;
        return switch (LoanType) {
            case 1 -> !(index > 0.8f);
            case 2 -> !(index > 0.7f);
            case 3 -> !(index > 0.6f);
            case 4 -> !(index > 0.5f);
            default -> false;
        };
    }

    public Boolean validate_r6(LocalDate birth, Integer Years){
        LocalDate actual = LocalDate.now();
        Integer ageTotal = Period.between(birth, actual).getYears();
        return ageTotal + Years <= 70;
    }

    public Integer validate_r7(Boolean r1, Boolean r2, Boolean r3, Boolean r4, Boolean r5){
        Integer total = r1.compareTo(false) + r2.compareTo(false) + r3.compareTo(false) + r4.compareTo(false) + r5.compareTo(false);
        if(total <= 2){
            //Se rechaza
            return 0;
        } else if (total == 5) {
            return 1;
        }else{
            return 2;
        }
    }
}
