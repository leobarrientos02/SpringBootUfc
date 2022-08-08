package com.example.ufc.fighter;

import com.example.ufc.sharedResources.Weightclass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FighterServiceTest {

    @Mock private FighterRepository fighterRepository;

    @Captor
    private ArgumentCaptor<Fighter> fighterArgumentCaptor;

    private FighterService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new FighterService(fighterRepository);
    }

    @Test
    @Disabled
    void itShouldFindFighterByName() {
        // Given
        // When
        // Then
    }

    @ParameterizedTest
    @CsvSource({
        "210, Heavyweight",
        "205, Light_Heavyweight",
        "185, Middleweight",
        "170, Welterweight",
        "155, Lightweight",
        "145, Featherweight",
        "135, Bantamweight",
        "125, Flyweight",
        "320, Unknown"
    })
    void itShouldGetWeightclassWhenWeightProvided(Double weight, Weightclass expected) {
        // When
        Weightclass weightclass = underTest.getWeightclass(weight);
        // Then
        assertThat(weightclass).isEqualTo(expected);
    }
}