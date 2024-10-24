package Tingeso.Proyecto.controllers;

import Tingeso.Proyecto.entities.TicketEntity;
import Tingeso.Proyecto.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/api/ticket")
@CrossOrigin("*")
public class TicketController {
    @Autowired
    private TicketService ticketServ;

    @PostMapping("/save")
    public ResponseEntity<TicketEntity> saveTicket(@RequestBody TicketEntity ticket) {
        TicketEntity ticketNew = ticketServ.addTicket(ticket);
        return ResponseEntity.ok(ticketNew);
    }

    @GetMapping("/getByID/{id}")
    public ResponseEntity<TicketEntity> getTicketById(@PathVariable Long id) {
        TicketEntity ticketfound = ticketServ.getById(id);
        return ResponseEntity.ok(ticketfound);
    }

    @PostMapping("/saveticket")
    public ResponseEntity<TicketEntity> saveTicketOld(@RequestBody TicketEntity ticket) {
        TicketEntity ticketNew = ticketServ.save(ticket);
        return ResponseEntity.ok(ticketNew);
    }

    @PostMapping("/cancel")
    public ResponseEntity<TicketEntity> cancelTicket(@RequestBody TicketEntity ticket) {
        TicketEntity ticketNew = ticketServ.cancelTicket(ticket);
        TicketEntity ticketNewSaved = ticketServ.save(ticketNew);
        return ResponseEntity.ok(ticketNewSaved);
    }

    @PostMapping("/reject")
    public ResponseEntity<TicketEntity> rejectTicket(@RequestBody TicketEntity ticket) {
        TicketEntity ticketNew = ticketServ.rejectTicket(ticket);
        TicketEntity ticketNewSaved = ticketServ.save(ticketNew);
        return ResponseEntity.ok(ticketNewSaved);
    }

    @PostMapping("/accept/executive")
    public ResponseEntity<TicketEntity> acceptTicketExecutive(@RequestBody TicketEntity ticket) {
        TicketEntity ticketNew = ticketServ.acceptTicketExecutive(ticket);
        TicketEntity ticketNewSaved = ticketServ.save(ticketNew);
        return ResponseEntity.ok(ticketNewSaved);
    }


    @PostMapping("/step")
    public ResponseEntity<TicketEntity> stepTicket(@RequestBody TicketEntity ticket) {
        ticket.setStep(ticket.getStep() + 1);
        TicketEntity ticketNew = ticketServ.save(ticket);
        return ResponseEntity.ok(ticketNew);
    }

    @PostMapping("/aprove")
    public ResponseEntity<TicketEntity> approveTicket(@RequestBody TicketEntity ticket) {
        TicketEntity ticketNew = ticketServ.approveTicket(ticket);
        TicketEntity ticketNewSaved = ticketServ.save(ticketNew);
        return ResponseEntity.ok(ticketNewSaved);
    }

    @GetMapping("/get/{id_user}")
    public ResponseEntity<List<TicketEntity>> getByUser(@PathVariable Integer id_user){
        List<TicketEntity> tickets = ticketServ.getByIdUser(id_user);
        return ResponseEntity.ok(tickets);
    }

    @PostMapping("/accept/user")
    public ResponseEntity<TicketEntity> acceptUserTicket(@RequestBody TicketEntity ticket) {
        TicketEntity ticketNew = ticketServ.acceptLoanUser(ticket);
        return ResponseEntity.ok(ticketNew);
    }

    @PostMapping("/save/aprove")
    public ResponseEntity<TicketEntity> aproveTicket(@RequestBody TicketEntity ticket) {
        TicketEntity ticketNew = ticketServ.approveTicket(ticket);
        return ResponseEntity.ok(ticketNew);
    }

    @GetMapping("/get/worker")
    public ResponseEntity<List<TicketEntity>> getByStatus(){
        List<TicketEntity> tickets = ticketServ.getAllByStatus();
        return ResponseEntity.ok(tickets);
    }

    //Falta comprobaciones y cambiar RequestBody por idticket y resto de cosas.
    @GetMapping("/validate/r1/{income}/{fee}")
    public ResponseEntity<Boolean> validateR1(@PathVariable int income, @PathVariable float fee) {
        Boolean resultado = ticketServ.Validater1(income,fee);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/validate/r3/{seniority}/{Independant}")
    public ResponseEntity<Boolean> validateR3(@PathVariable Integer seniority, @PathVariable boolean Independant) {
        Boolean resultado = ticketServ.Validate_r3(seniority, Independant);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/validate/r4/{fee}/{debt}/{income}")
    public ResponseEntity<Boolean> validateR4(@PathVariable Float fee, @PathVariable Float debt, @PathVariable Float income) {
        Boolean resultado = ticketServ.Validate_r4(fee, debt, income);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/validate/r5/{Amount}/{type}/{Valuation}")
    public ResponseEntity<Boolean> validateR5(@PathVariable float Amount, @PathVariable Integer type, @PathVariable float Valuation) {
        Boolean resultado = ticketServ.Validate_r5(Amount, type, Valuation);
        return ResponseEntity.ok(resultado);
    }


    @GetMapping("/validate/r6/{birth}/{years}")
    public ResponseEntity<Boolean> validateR6(@PathVariable LocalDate birth, @PathVariable Integer years) {
        Boolean resultado = ticketServ.validate_r6(birth, years);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/validate/r7/{r1}/{r2}/{r3}/{r4}/{r5}")
    public ResponseEntity<Integer> validateR7(@PathVariable Boolean r1, @PathVariable Boolean r2, @PathVariable Boolean r3, @PathVariable Boolean r4, @PathVariable Boolean r5 ) {
        Integer resultado = ticketServ.validate_r7(r1,r2,r3,r4,r5);
        return ResponseEntity.ok(resultado);
    }

}
