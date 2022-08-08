package com.example.ufc.fight;

import com.example.ufc.fighter.Fighter;
import com.example.ufc.fighter.FighterRepository;
import com.example.ufc.referee.RefereeRepository;
import com.example.ufc.sharedResources.FightType;
import com.example.ufc.sharedResources.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static com.example.ufc.sharedResources.Weightclass.Light_Heavyweight;
import static java.util.Calendar.DECEMBER;
import static java.util.Calendar.FEBRUARY;
import static org.assertj.core.api.Assertions.assertThat;

class FightServiceTest {

    @Mock FightRepository fightRepository;
    @Mock FighterRepository fighterRepository;
    @Mock RefereeRepository refereeRepository;

    private FightService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new FightService(fightRepository, fighterRepository, refereeRepository);
    }

    @Test
    void itShouldMakeCorrectFinalPredictionDifference() {
        // Given
        Fighter fighter1 = new Fighter(1L,
                "Fighter1", 22, LocalDate.of(2000, DECEMBER, 18),
                205.0, "5ft 11in", 69.0, Light_Heavyweight,
                LocalDate.of(2020, FEBRUARY, 4),
                "Test City, Test",
                "test");
        Fighter fighter2 = new Fighter(2L,
                "Fighter2", 30, LocalDate.of(1990, DECEMBER, 18),
                200.0, "5ft 7in", 50.0, Light_Heavyweight,
                LocalDate.of(2020, FEBRUARY, 4),
                "Test City, Test",
                "test");

        // When
        String prediction = underTest.finalPrediction(fighter1,fighter2, "championship");
        String expected = fighter1.getName() + " is the favorite";

        // Then
        assertThat(prediction).isEqualTo(expected);
    }

    @Test
    void itShouldChooseFighterTwoForFinalPrediction() {
        // Given
        Fighter fighter1 = new Fighter(1L,
                "Fighter1", 33, LocalDate.of(1990, DECEMBER, 18),
                194.0, "5ft 3in", 69.0, Light_Heavyweight,
                LocalDate.of(2010, FEBRUARY, 4),
                "Test City, Test",
                "test");
        Fighter fighter2 = new Fighter(2L,
                "Fighter2", 21, LocalDate.of(2001, DECEMBER, 18),
                205.0, "6ft 1in", 80.0, Light_Heavyweight,
                LocalDate.of(2020, FEBRUARY, 4),
                "Test City, Test",
                "test");

        // When
        String prediction = underTest.finalPrediction(fighter1,fighter2, "championship");
        String expected = fighter2.getName() + " is the favorite";

        // Then
        assertThat(prediction).isEqualTo(expected);
    }
    @Test
    void itShouldEffectPredictionDueToMajorHeight() {
        // Given
        Fighter fighter1 = new Fighter(1L,
                "Fighter1", 22, LocalDate.of(2000, DECEMBER, 18),
                205.0, "6ft 2in", 69.0, Light_Heavyweight,
                LocalDate.of(2020, FEBRUARY, 4),
                "Test City, Test",
                "test");
        Fighter fighter2 = new Fighter(2L,
                "Fighter2", 30, LocalDate.of(1990, DECEMBER, 18),
                200.0, "5ft 2in", 50.0, Light_Heavyweight,
                LocalDate.of(2020, FEBRUARY, 4),
                "Test City, Test",
                "test");

        // When
        String prediction = underTest.finalPrediction(fighter1,fighter2, "championship");
        String expected = fighter1.getName() + " is the favorite";

        // Then
        assertThat(prediction).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "three rounds, Fighter1 is the favorite",
            "five rounds, Fighter1 is the favorite",
            "championship, Fighter1 is the favorite"
    })
    void itShouldEffectPredictionDueToFightType(String fightType, String expected) {
        // Given
        Fighter fighter1 = new Fighter(1L,
                "Fighter1", 22, LocalDate.of(2000, DECEMBER, 18),
                205.0, "6ft 2in", 69.0, Light_Heavyweight,
                LocalDate.of(2020, FEBRUARY, 4),
                "Test City, Test",
                "test");
        Fighter fighter2 = new Fighter(1L,
                "Fighter2", 30, LocalDate.of(1990, DECEMBER, 18),
                200.0, "5ft 2in", 50.0, Light_Heavyweight,
                LocalDate.of(2020, FEBRUARY, 4),
                "Test City, Test",
                "test");

        // When
        String prediction = underTest.finalPrediction(fighter1,fighter2, fightType);
        // Then
        assertThat(prediction).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "THREE_ROUNDS, 3 rounds",
            "FIVE_ROUNDS, 5 rounds",
            "CHAMPIONSHIP, Championship"
    })
    void itShouldConvertFightTypeOnUserInput(String userInput, String expected) {
        // When
        String fightType = underTest.fightTypeConverter(userInput);

        // Then
        assertThat(fightType).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "decision, DECISION",
            "split decision, SPLIT_DECISION",
            "ko, KO",
            "tko, TKO",
            "draw, DRAW",
            "doctor stoppage, DOCTOR_STOPPAGE",
            "tba, TBA",
            "test, NONE"
    })
    void itShouldGetResultOnUserInput(String input, Result expected) {
        // When
        Result result = underTest.getResult(input);

        // Then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "three rounds, THREE_ROUNDS",
            "five rounds, FIVE_ROUNDS",
            "championship, CHAMPIONSHIP",
            "test, UNKNOWN"
    })
    void itShouldGetFightType(String userInput, FightType expected) {
        // When
        FightType fightType = underTest.getFightType(userInput);

        // Then
        assertThat(fightType).isEqualTo(expected);
    }
}