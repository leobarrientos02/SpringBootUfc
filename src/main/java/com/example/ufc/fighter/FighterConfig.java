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
                "5ft 11 in",
                "69 in",
                Featherweight,
                    LocalDate.of(2012, FEBRUARY, 4),
                    "Waianae, Hawaii, United States",
                    "https://dmxg5wxfqgb4u.cloudfront.net/styles/athlete_matchup_stats_full_body/s3/2022-06/5e67898b-785a-4e9b-9689-57822748fc6f%252FHOLLOWAY_MAX_R_07-02.png?itok=dFI1r_2A"
            );

            Fighter alexanderVolkanovski = new Fighter(
                "Alexander Volkanovski",
                33,
                LocalDate.of(1988, SEPTEMBER, 29),
                145.5,
                "5ft 6in,",
                "71 in",
                Featherweight,
                    LocalDate.of(2016, NOVEMBER, 26),
                    "Windang, New South Wales, Australia",
                    "https://dmxg5wxfqgb4u.cloudfront.net/styles/athlete_bio_full_body/s3/2022-04/0984640e-8ef9-4283-afc3-d64ac0a0240a%252FVOLKANOVSKI_ALEXANDER_L_BELT_04-09.png?itok=2jQs8w9O"
            );

            // Save to database
            repository.saveAll(List.of(
                    maxHalloway,
                    alexanderVolkanovski
            ));
        };
    }
}
