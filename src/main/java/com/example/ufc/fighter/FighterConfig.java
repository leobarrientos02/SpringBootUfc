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
                    "https://dmxg5wxfqgb4u.cloudfront.net/styles/gallery_collapsed_mobile_1x/s3/2022-06/063022-max-holloway-training-ufc-pi-33.jpg?itok=QJ4ppi6p"
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
                    "https://www.sherdog.com/image_crop/200/300/_images/fighter/20220325081549_Alexander_Volkanovski_ff.JPG"
            );

            // Save to database
            repository.saveAll(List.of(
                    maxHalloway,
                    alexanderVolkanovski
            ));
        };
    }
}
