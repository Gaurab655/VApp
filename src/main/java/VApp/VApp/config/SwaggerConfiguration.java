package VApp.VApp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customConfig(){
        return new OpenAPI().info(
                new Info().title("Bank Management System")
                        .description("by Gaurab ")
        );
    }
}
