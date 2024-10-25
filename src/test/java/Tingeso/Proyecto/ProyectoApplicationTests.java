package Tingeso.Proyecto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"DB_HOST = 172.27.192.1"})
public class ProyectoApplicationTests {

    @Test
    public void contextLoads() {
        // Esta prueba verifica que el contexto de Spring se cargue correctamente.
        // Si hay algún problema de configuración, este test fallará.
    }
}
