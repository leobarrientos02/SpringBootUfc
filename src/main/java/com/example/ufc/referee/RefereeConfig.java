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
                    "Herb Dean",
                    LocalDate.of(1970, SEPTEMBER, 30),
                    51,
                    "https://ftw.usatoday.com/wp-content/uploads/sites/90/2020/07/herb.jpg?w=1000&h=600&crop=1"
            );

            // Save to database
            repository.save(herbDean);
        };
    }
}
