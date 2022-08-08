package com.example.ufc.fight;

import com.example.ufc.fighter.Fighter;
import com.example.ufc.fighter.FighterRepository;
import com.example.ufc.referee.Referee;
import com.example.ufc.referee.RefereeRepository;
import com.example.ufc.sharedResources.FightType;
import com.example.ufc.sharedResources.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.ufc.sharedResources.FightType.*;
import static com.example.ufc.sharedResources.Result.*;

@Service
public class FightService {

    private final FightRepository fightRepository;
    private final FighterRepository fighterRepository;

    private final RefereeRepository refereeRepository;

    @Autowired
    public FightService(FightRepository fightRepository, FighterRepository fighterRepository, RefereeRepository refereeRepository) {
        this.fightRepository = fightRepository;
        this.fighterRepository = fighterRepository;
        this.refereeRepository = refereeRepository;
    }

    public List<Fight> getAllFights(){
        return fightRepository.findAll();
    }

    public void addFight(FightDto dto) {
        Fight fight = new Fight();
        Optional<Fighter> fighter1 = fighterRepository.findFighterByName(dto.getFighter1());
        Optional<Fighter> fighter2 = fighterRepository.findFighterByName(dto.getFighter2());
        if(!fighter1.isPresent()){
            throw new IllegalStateException(dto.getFighter1() + " was not found");
        }else if(!fighter2.isPresent()){
            throw new IllegalStateException(dto.getFighter2() + " was not found");
        }else{
            fight.setFighter1(fighter1.get());
            fight.setFighter2(fighter2.get());
        }

        Optional<Referee> referee = refereeRepository.findRefereeByName(dto.getRefereeName());
        if(!referee.isPresent()) {
            throw new IllegalStateException(dto.getRefereeName() + " was not found");
        }else{
            fight.setReferee(referee.get());
        }

        if(dto.getDate() != null){
            fight.setDate(dto.getDate());
        }else{
            throw new IllegalStateException("Please enter a date");
        }

        fight.setDescription(
                fighter1.get().getName() + " is set to fight " +
                        fighter2.get().getName() +  " in a " +
                        fighter1.get().getWeightclass() + " " +
                        this.fightTypeConverter(dto.getFightType()) + " fight in the " +
                        dto.getLocation() + " in " +
                        dto.getDate().getMonth() + " " + dto.getDate().getDayOfMonth() + " " +
                        dto.getDate().getYear() + " with " +
                        referee.get().getName() + " as the referee. Looking at the betting odds " +
                        this.finalPrediction(fighter1.get(), fighter2.get(), dto.getFightType())
        );

        if(dto.getLocation() == null){
            throw new IllegalStateException("Please enter a location");
        }else{
            fight.setLocation(dto.getLocation());
        }

        if(dto.getFightType() == null){
            throw new IllegalStateException("Please enter a fight type");
        }else{
            fight.setFightType(this.getFightType(dto.getFightType()));
        }

        fight.setResult(TBA);

        fightRepository.save(fight);

    }

    public void deleteFight(Long fightId) {
        boolean exists = fighterRepository.existsById(fightId);
        if(!exists){
            throw new IllegalStateException("Fight with the id of " + fightId + " does not exist");
        }else{
            fightRepository.deleteById(fightId);
        }
    }
    @Transactional
    public void updateFight(Long fightId, FightDto dto) {
        Fight fight = fightRepository.findById(fightId)
                .orElseThrow(()-> new IllegalStateException(
                        "Fight with the id of " + fightId + " does not exist"));

        Optional<Fighter> fighter1 = fighterRepository.findFighterByName(dto.getFighter1());
        Optional<Fighter> fighter2 = fighterRepository.findFighterByName(dto.getFighter2());
        if(!fighter1.isPresent()){
            throw new IllegalStateException(dto.getFighter1() + " was not found");
        }else if(!fighter2.isPresent()){
            throw new IllegalStateException(dto.getFighter2() + " was not found");
        }else{
            fight.setFighter1(fighter1.get());
            fight.setFighter2(fighter2.get());

        }

        Optional<Referee> referee = refereeRepository.findRefereeByName(dto.getRefereeName());
        if(!referee.isPresent()) {
            throw new IllegalStateException(dto.getRefereeName() + " was not found");
        }else{
            fight.setReferee(referee.get());
        }

        if(dto.getDate() != null){
            fight.setDate(dto.getDate());
        }else{
            throw new IllegalStateException("Please enter a date");
        }

        fight.setDescription(
                fighter1.get().getName() + " is set to fight " +
                        fighter2.get().getName() +  " in a " +
                        fighter1.get().getWeightclass() + " " +
                        this.fightTypeConverter(dto.getFightType()) + " fight in the " +
                        dto.getLocation() + " in " +
                        dto.getDate().getMonth() + " " + dto.getDate().getDayOfMonth() + " " +
                        dto.getDate().getYear() + " with " +
                        referee.get().getName() + " as the referee. Looking at the betting odds " +
                        this.finalPrediction(fighter1.get(), fighter2.get(), dto.getFightType())
        );

        if(dto.getLocation() == null){
            throw new IllegalStateException("Please enter a location");
        }else{
            fight.setLocation(dto.getLocation());
        }

        if(dto.getFightType() == null){
            throw new IllegalStateException("Please enter a fight type");
        }else{
            fight.setFightType(this.getFightType(dto.getFightType()));
        }

        fight.setResult(TBA);
    }

    @Transactional
    public void updateResult(Long fightId, ResultDto dto){
        Fight fight = fightRepository.findById(fightId)
                .orElseThrow(()-> new IllegalStateException(
                        "Fight with the id of " + fightId + " does not exist"));

        Optional<Fighter> winner = fighterRepository.findFighterByName(dto.getWinner());
        if(!winner.isPresent()){
            throw new IllegalStateException(dto.getWinner() + " was not found");
        }else{
            fight.setWinner(winner.get());
        }
        fight.setResult(this.getResult(dto.getResult()));
    }

    public Optional<Fight> getFightById(Long fightId) {
        return fightRepository.findById(fightId);
    }

    public String finalPrediction(Fighter fighter1, Fighter fighter2, String fightType){
        int fighter1Points = 0;
        int fighter2Points = 0;

        // Checking the weight
        if(fighter1.getWeight() > fighter2.getWeight()){
            fighter1Points = fighter1Points + 2;
        }else {
            fighter2Points = fighter2Points + 2;
        }

        // Checking the age
        int ageDifference = 0;
        if(fighter1.getAge() < fighter2.getAge()){
            ageDifference = fighter2.getAge() - fighter1.getAge();
            if(ageDifference > 5){
                fighter1Points = fighter1Points + 2;
            }else{
                fighter1Points = fighter1Points + 1;
            }
        }else{
            ageDifference = fighter1.getAge() - fighter2.getAge();
            if(ageDifference > 5){
                fighter2Points = fighter2Points + 2;
            }else{
                fighter2Points = fighter2Points + 1;
            }
        }

        // Checking the height
        int height1 = Integer.parseInt(fighter1.getHeight().replaceAll("[\\D]", ""));
        int height2 = Integer.parseInt(fighter2.getHeight().replaceAll("[\\D]", ""));
        int heightDifference = 0;
        if(height1 > height2){
            heightDifference = height1 - height2;
            if(heightDifference > 6){
                fighter1Points = fighter1Points + 2;
            }else{
                fighter1Points = fighter1Points + 1;
            }
        }else{
            heightDifference = height2 - height1;
            if(heightDifference > 6){
                fighter2Points = fighter2Points + 2;
            }else{
                fighter2Points = fighter2Points + 1;
            }
        }

        // Checking the reach
        double reachDifference = 0;
        if(fighter1.getReach() > fighter2.getReach()){
            reachDifference = fighter1.getReach() - fighter2.getReach();
            if(reachDifference > 4){
                fighter1Points = fighter1Points + 2;
            }else{
                fighter1Points = fighter1Points + 1;
            }
        }else{
            reachDifference = fighter2.getReach() - fighter1.getReach();
            if(reachDifference > 4){
                fighter1Points = fighter1Points + 2;
            }else{
                fighter1Points = fighter1Points + 1;
            }
        }

        // Check debut for experience points
        int fighter1Experience = Period.between(fighter1.getDebut(), LocalDate.now()).getYears();
        int fighter2Experience = Period.between(fighter2.getDebut(), LocalDate.now()).getYears();
        int experienceDifference = 0;
        if(fighter1Experience > fighter2Experience){
            experienceDifference = fighter1Experience - fighter2Experience;
            if(experienceDifference > 5){
                fighter1Points = fighter1Points + 2;
            }else{
                fighter1Points = fighter1Points + 1;
            }
        }else {
            experienceDifference = fighter2Experience - fighter1Experience;
            if(experienceDifference > 5){
                fighter2Points = fighter2Points + 2;
            }else{
                fighter2Points = fighter2Points + 1;
            }
        }

        // Fight Type advantage
        if(fightType.equals("three rounds")) {
            if (fighter1.getWeight() > fighter2.getWeight()) {
                fighter1Points = fighter1Points + 1;
            } else {
                fighter2Points = fighter2Points + 1;
            }
        } else if (fightType.equals("five rounds")) {
            if(fighter1.getWeight() < fighter2.getWeight()){
                fighter1Points = fighter1Points + 1;
            }else{
                fighter2Points = fighter2Points + 1;
            }
        }else{
            if(fighter1Experience > fighter2Experience){
                fighter1Points = fighter1Points + 1;
            }else{
                fighter2Points = fighter2Points + 1;
            }
        }

        if(fighter1Points > fighter2Points){
            return fighter1.getName() + " is the favorite";
        }else{
            return fighter2.getName() + " is the favorite";
        }
    }

    public String fightTypeConverter(String fightType){
        String clean = "";
        if(fightType.equals("THREE_ROUNDS")){
            clean = "3 rounds";
        }else if(fightType.equals("FIVE_ROUNDS")){
            clean = "5 rounds";
        }else{
            clean = "Championship";
        }

        return clean;
    }

    public Result getResult(String result){
        switch (result.toLowerCase()) {
            case "decision":
                return DECISION;
            case "split decision":
                return SPLIT_DECISION;
            case "ko":
                return KO;
            case "tko":
                return TKO;
            case "draw":
                return DRAW;
            case "doctor stoppage":
                return DOCTOR_STOPPAGE;
            case "tba":
                return TBA;
            default:
                return NONE;
        }
    }
    public FightType getFightType(String fightType){
        switch (fightType.toLowerCase()) {
            case "three rounds":
                return THREE_ROUNDS;
            case "five rounds":
                return FIVE_ROUNDS;
            case "championship":
                return CHAMPIONSHIP;
            default:
                return UNKNOWN;
        }
    }
}
