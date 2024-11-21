package com.app.scheduling;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite requisições para todas as URLs do servidor
                .allowedOrigins("http://192.168.0.100:8080", "http://localhost:8080") // Adicione os IPs/URLs permitidos
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
                .allowedHeaders("*") // Todos os headers são permitidos
                .allowCredentials(true); // Se necessário permitir cookies e credenciais
    }
}
