package Tingeso.Proyecto.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TicketEntityTest {

    @Test
    public void testTicketEntityConstructor() {
        Integer id_user = 1;
        Integer amount = 10000;
        Integer years = 5;
        Float fee = 2.5f;
        Integer type = 2;
        LocalDate user_birth = LocalDate.of(1990, 1, 1);

        TicketEntity ticket = new TicketEntity(id_user, amount, years, fee, type, user_birth);

        assertThat(ticket.getUsuario()).isEqualTo(id_user);
        assertThat(ticket.getAmount()).isEqualTo(amount);
        assertThat(ticket.getYears()).isEqualTo(years);
        assertThat(ticket.getFee()).isEqualTo(fee);
        assertThat(ticket.getType()).isEqualTo(type);
        assertThat(ticket.getUser_birth()).isEqualTo(user_birth);
    }
}
