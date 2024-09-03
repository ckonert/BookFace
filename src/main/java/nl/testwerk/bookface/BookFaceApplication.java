package nl.testwerk.bookface;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootApplication
public class BookFaceApplication {

    public static void main(String... args) {

        log.info("Starting the BookFace...");
        SpringApplication.run(BookFaceApplication.class, args);
        log.info("The BookFace has started...");
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
