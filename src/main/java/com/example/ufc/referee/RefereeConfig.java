package com.example.ufc.referee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

import static java.util.Calendar.*;

@Configuration
public class RefereeConfig {

    @Bean
    CommandLineRunner refereeCommandLineRunner(RefereeRepository repository){
        return args -> {
            Referee herbDean = new Referee(
                    1L,
                    "Herb Dean",
                    LocalDate.of(1970, SEPTEMBER, 30),
                    51
            );

            // Save to database
            repository.save(herbDean);
        };
    }
}
