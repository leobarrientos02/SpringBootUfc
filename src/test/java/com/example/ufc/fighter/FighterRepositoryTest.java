package com.example.ufc.fighter;

import com.example.ufc.sharedResources.Weightclass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static com.example.ufc.sharedResources.Weightclass.Featherweight;
import static java.util.Calendar.DECEMBER;
import static java.util.Calendar.FEBRUARY;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
class FighterRepositoryTest {

    @Autowired
    private FighterRepository underTest;

    @Test
    void itShouldSaveFighter() {
        // Given
        Long fighterId = 1L;
        String name = "Test Test";
        Integer age = 24;
        LocalDate birthDate = LocalDate.of(1991, DECEMBER, 4);
        Double weight = 212.0;
        String height = "5ft 11in";
        Double reach = 69.0;
        LocalDate debut = LocalDate.of(2012, FEBRUARY, 4);
        String fightingOutOf = "Test City, Test";
        String imageUrl = "https://miro.medium.com/max/4800/1*J8sjpKQJswCKiPUYVefbgQ.jpeg";
        Fighter fighter = new Fighter(fighterId, name,age,birthDate,weight,height,reach, Featherweight,debut,fightingOutOf,imageUrl);

        // When
        underTest.save(fighter);

        // Then
        Optional<Fighter> optionalFighter = underTest.findById(fighterId);
        assertThat(optionalFighter).isPresent()
                .hasValueSatisfying(f -> {
                    assertThat(f).isEqualTo(fighter);
                });
    }
}