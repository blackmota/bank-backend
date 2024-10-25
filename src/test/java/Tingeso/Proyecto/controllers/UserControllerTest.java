package Tingeso.Proyecto.controllers;

import Tingeso.Proyecto.entities.UserEntity;
import Tingeso.Proyecto.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser_Success() {
        UserEntity user = new UserEntity();
        UserEntity savedUser = new UserEntity();
        when(userService.registerUser(user)).thenReturn(savedUser);

        ResponseEntity<UserEntity> response = userController.saveUser(user);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(savedUser);
        verify(userService, times(1)).registerUser(user);
    }

    @Test
    public void testSaveUser_Failure() {
        UserEntity user = new UserEntity();
        when(userService.registerUser(user)).thenReturn(null);

        ResponseEntity<UserEntity> response = userController.saveUser(user);

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody()).isNull();
        verify(userService, times(1)).registerUser(user);
    }

    @Test
    public void testLogin_Success() {
        UserEntity user = new UserEntity();
        when(userService.loginUser("12345678-9", "password")).thenReturn(user);

        ResponseEntity<UserEntity> response = userController.login("12345678-9", "password");

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(user);
        verify(userService, times(1)).loginUser("12345678-9", "password");
    }

    @Test
    public void testLogin_Failure() {
        when(userService.loginUser("12345678-9", "wrong_password")).thenReturn(null);

        ResponseEntity<UserEntity> response = userController.login("12345678-9", "wrong_password");

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody()).isNull();
        verify(userService, times(1)).loginUser("12345678-9", "wrong_password");
    }

    @Test
    public void testGetUser_Success() {
        UserEntity user = new UserEntity();
        when(userService.getUser(1L)).thenReturn(user);

        ResponseEntity<UserEntity> response = userController.getUser(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(user);
        verify(userService, times(1)).getUser(1L);
    }

}
