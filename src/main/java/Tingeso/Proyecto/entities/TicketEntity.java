package Tingeso.Proyecto.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="ticket")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketEntity {
    //All the information given By the user
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer usuario;
    private Integer amount;
    private Integer years;
    private Float fee;
    private Float interest;
    private Integer type;
    private LocalDate user_birth;

    //All the status and step about the process, status->"E3", step 3
    private String status;
    private Integer step;


    public TicketEntity(Integer id_user, Integer amount, Integer years, Float fee, Integer type, LocalDate user_birth) {
        this.usuario = id_user;
        this.amount = amount;
        this.years = years;
        this.fee = fee;
        this.type = type;
        this.user_birth = user_birth;
    }
}
