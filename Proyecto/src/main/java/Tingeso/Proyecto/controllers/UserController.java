package Tingeso.Proyecto.controllers;

import Tingeso.Proyecto.entities.UserEntity;
import Tingeso.Proyecto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity user) {
        UserEntity usernew = userService.registerUser(user);
        if (usernew == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(usernew);
    }

    @GetMapping("/login")
    public ResponseEntity<UserEntity> login(@RequestParam String rut, @RequestParam String password) {
        UserEntity user = userService.loginUser(rut,password);
        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long id) {
        UserEntity userFound = userService.getUser(id);
        return ResponseEntity.ok(userFound);
    }
}

