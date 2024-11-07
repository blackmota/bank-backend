package Tingeso.Proyecto;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class ProyectoApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        // Verificar que el contexto se haya cargado y la aplicación esté en funcionamiento
        assertThat(applicationContext).isNotNull();
    }

    @Test
    void mainTest() {
        ProyectoApplication.main(new String[] {});  // Llama a main con un array vacío
    }
}
