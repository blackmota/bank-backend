package Tingeso.Proyecto.repositories;

import Tingeso.Proyecto.entities.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity,Long> {
        DocumentEntity findByTicketidAndType(Integer ticketid, Integer type);
        List<DocumentEntity> findAllByTicketid(Integer ticketid);
}
