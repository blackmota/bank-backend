package Tingeso.Proyecto.repositories;

import Tingeso.Proyecto.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public UserEntity findById(long id);
    public UserEntity findByRut(String Rut);
    List<UserEntity> findByRole(Integer Role);
}
