package com.example.ufc.fighter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static com.example.ufc.sharedResources.Weightclass.*;
import static java.util.Calendar.*;

@Configuration
public class FighterConfig {

    @Bean
    CommandLineRunner fighterCommandLineRunner(FighterRepository repository){
        return args -> {
            Fighter maxHalloway = new Fighter(
                "Max Halloway",
                30,
                LocalDate.of(1991, DECEMBER, 4),
                145.0,
                "5'11 inches",
                Featherweight
            );
            Fighter alexanderVolkanovski = new Fighter(
                "Alexander Volkanovski",
                33,
                LocalDate.of(1988, SEPTEMBER, 29),
                145.5,
                "5'6 inches",
                Featherweight
            );

            // Save to database
            repository.saveAll(List.of(
                    maxHalloway,
                    alexanderVolkanovski
            ));
        };
    }
}
