package Tingeso.Proyecto.services;

import Tingeso.Proyecto.entities.UserEntity;
import Tingeso.Proyecto.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository usersRepo;

    public UserEntity registerUser(UserEntity user) {
        UserEntity userFound = usersRepo.findByRut(user.getRut());
        if (userFound != null) {
            return null;
        }
        return usersRepo.save(user);
    }

    public UserEntity loginUser(String rut, String password) {
        UserEntity userFound = usersRepo.findByRut(rut);
        if (userFound != null) {
            if (userFound.getPassword().equals(password)) {
                return userFound;
            }
            return null;
        }
        return null;
    }

    public UserEntity getUser(Long id) {
        return usersRepo.findById(id).get();
    }


}
