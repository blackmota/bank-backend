package Tingeso.Proyecto.services;

import Tingeso.Proyecto.entities.UserEntity;
import Tingeso.Proyecto.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void whenRegisterUser_UserNotFound_thenRegisterUser() {
        // Given
        UserEntity user = new UserEntity();
        user.setRut("12345678-9");
        user.setPassword("password");

        // Simular que el usuario no se encuentra en la base de datos
        when(userRepository.findByRut(user.getRut())).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);

        // When
        UserEntity result = userService.registerUser(user);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getRut()).isEqualTo("12345678-9");
    }

    @Test
    void whenRegisterUser_UserAlreadyExists_thenDoNotRegisterUser() {
        // Given
        UserEntity existingUser = new UserEntity();
        existingUser.setRut("12345678-9");
        existingUser.setPassword("password");

        // Simular que el usuario ya existe en la base de datos
        when(userRepository.findByRut(existingUser.getRut())).thenReturn(existingUser);

        // When
        UserEntity result = userService.registerUser(existingUser);

        // Then
        assertThat(result).isNull();
    }

    @Test
    void whenLoginUser_ValidCredentials_thenReturnUser() {
        // Given
        UserEntity user = new UserEntity();
        user.setRut("12345678-9");
        user.setPassword("password");

        // Simular que el usuario existe en la base de datos
        when(userRepository.findByRut(user.getRut())).thenReturn(user);

        // When
        UserEntity result = userService.loginUser("12345678-9", "password");

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getRut()).isEqualTo("12345678-9");
    }

    @Test
    void whenLoginUser_InvalidCredentials_thenReturnNull() {
        // Given
        UserEntity user = new UserEntity();
        user.setRut("12345678-9");
        user.setPassword("password");

        // Simular que el usuario existe en la base de datos
        when(userRepository.findByRut(user.getRut())).thenReturn(user);

        // When
        UserEntity result = userService.loginUser("12345678-9", "wrongpassword");

        // Then
        assertThat(result).isNull();
    }

    @Test
    void whenLoginUser_UserNotFound_thenReturnNull() {
        // Simular que el usuario no se encuentra en la base de datos
        when(userRepository.findByRut("12345678-9")).thenReturn(null);

        // When
        UserEntity result = userService.loginUser("12345678-9", "password");

        // Then
        assertThat(result).isNull();
    }

    @Test
    public void testGetUser() {
        //Given
        Long userId = 1L;
        UserEntity expectedUser = new UserEntity();
        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        //When
        UserEntity result = userService.getUser(userId);

        //Then
        assertThat(result).isNotNull()
                .isEqualTo(expectedUser);
        verify(userRepository).findById(userId);
    }
}
