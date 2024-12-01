package sk.ustinov.animals;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@OpenAPIDefinition(
        info = @Info(
                title = "Animal Management API",
                version = "0.0.1",
                description = "API for managing animals, including CRUD operations and detailed data retrieval.",
                contact = @Contact(
                        name = "Support Team",
                        email = "roman@ustinov.sk"
                )
        ),
        servers = @Server(url = "http://localhost:8089")
)
public class AnimalsApplication {

    public static void main(String... args) {
        SpringApplication.run(AnimalsApplication.class, args);
    }

}
