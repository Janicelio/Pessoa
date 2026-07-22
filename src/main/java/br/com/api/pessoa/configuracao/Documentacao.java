package br.com.api.pessoa.configuracao;

// Importações
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Define que esta classe contém configurações para a aplicação Spring
@Configuration
public class Documentacao {
    
    // Define um bean do tipo OpenAPI que será gerenciado pelo Spring
    @Bean
    public OpenAPI customOpenAPI() {
        // Cria uma instância de OpenAPI com informações personalizadas da sua API
        return new OpenAPI()
            .info(new Info()
                // Título da documentação da API que aparecerá no Swagger UI
                .title("API de Pessoas")
                // Descrição da API, explicando seu propósito
                .description("API para gerenciamento de pessoas")
                // Versão da API (importante para controle e versionamento)
                .version("1.0")
                // Informações de contato do desenvolvedor ou da equipe responsável
                .contact(new Contact()
                    .name("Janicélio Pereira Dutra")  // Nome do responsável
                    .email("janicelio@yahoo.com.br") // E-mail de contato
                    .url("https://ralflima.com")       // Site ou link de referência
                )
            );
    }
}
