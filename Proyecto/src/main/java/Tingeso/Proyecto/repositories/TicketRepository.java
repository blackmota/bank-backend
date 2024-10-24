package Tingeso.Proyecto.repositories;

import Tingeso.Proyecto.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    List<TicketEntity> getAllByUsuario(Integer usuario);
    TicketEntity getById(Long id);
    List<TicketEntity> getAllByStatusNotInOrderByStepDesc(List<String> status);
}
