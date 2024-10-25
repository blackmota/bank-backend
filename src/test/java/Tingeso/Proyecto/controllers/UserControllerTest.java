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
        //Given
        UserEntity user = new UserEntity();
        UserEntity savedUser = new UserEntity();
        when(userService.registerUser(user)).thenReturn(savedUser);

        //When
        ResponseEntity<UserEntity> response = userController.saveUser(user);

        //Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(savedUser);
        verify(userService, times(1)).registerUser(user);
    }

    @Test
    public void testSaveUser_Failure() {
        //Given
        UserEntity user = new UserEntity();
        when(userService.registerUser(user)).thenReturn(null);

        //When
        ResponseEntity<UserEntity> response = userController.saveUser(user);

        //That
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody()).isNull();
        verify(userService, times(1)).registerUser(user);
    }

    @Test
    public void testLogin_Success() {
        //Given
        UserEntity user = new UserEntity();
        when(userService.loginUser("12345678-9", "password")).thenReturn(user);

        //When
        ResponseEntity<UserEntity> response = userController.login("12345678-9", "password");

        //That
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(user);
        verify(userService, times(1)).loginUser("12345678-9", "password");
    }

    @Test
    public void testLogin_Failure() {
        //Given
        when(userService.loginUser("12345678-9", "wrong_password")).thenReturn(null);

        //When
        ResponseEntity<UserEntity> response = userController.login("12345678-9", "wrong_password");

        //Then
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        assertThat(response.getBody()).isNull();
        verify(userService, times(1)).loginUser("12345678-9", "wrong_password");
    }

    @Test
    public void testGetUser_Success() {

        //Given
        UserEntity user = new UserEntity();
        when(userService.getUser(1L)).thenReturn(user);

        //When
        ResponseEntity<UserEntity> response = userController.getUser(1L);

        //Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(user);
        verify(userService, times(1)).getUser(1L);
    }

}
