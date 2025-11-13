package upeu.edu.pe.Producto.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST - Gestión de Productos")
                        .version("1.0.0")
                        .description("API RESTful para gestión de productos con Spring Boot y PostgreSQL. " +
                                "Permite realizar operaciones CRUD completas, búsquedas por nombre y categoría, " +
                                "eliminación lógica y física de productos.")
                        .contact(new Contact()
                                .name("Juan")
                                .email("enrique.sanchez@upeu.edu.pe")
                                .url("https://github.com/juancholover"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de Desarrollo"),
                        new Server()
                                .url("http://10.0.2.2:8080")
                                .description("Servidor para Android Emulator")
                ));
    }
}
