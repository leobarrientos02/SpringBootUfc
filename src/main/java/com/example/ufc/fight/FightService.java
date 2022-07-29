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
import java.util.List;
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
                        this.finalPrediction(fighter1.get(), fighter2.get())
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
                        this.finalPrediction(fighter1.get(), fighter2.get())
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

    public String finalPrediction(Fighter fighter1, Fighter fighter2){
        int fighter1Advantage = 0;
        int fighter2Advantage = 0;
        if(fighter1.getAge() < fighter2.getAge()){
            fighter1Advantage = fighter1Advantage  + 1;
        }else{
            fighter2Advantage = fighter2Advantage  + 1;
        }

        if(fighter1.getWeight() < fighter2.getWeight()){
            fighter1Advantage = fighter1Advantage  + 1;
        }else{
            fighter2Advantage = fighter2Advantage  + 1;
        }

        int height1 = Integer.parseInt(fighter1.getHeight().replaceAll("[\\D]", ""));
        int height2 = Integer.parseInt(fighter2.getHeight().replaceAll("[\\D]", ""));
        if(height1 > height2){
            fighter1Advantage = fighter1Advantage  + 1;
        }else{
            fighter2Advantage = fighter2Advantage  + 1;
        }

        if(fighter1Advantage < fighter2Advantage){
            String winBy = "";
            if(fighter1.getWeight() > fighter2.getWeight()){
                winBy = "knockout";
            }else if(fighter1.getAge() > fighter2.getAge()){
                winBy = "split decision";
            }else if(height1 > height2){
                winBy = "tko";
            }
            return fighter1.getName() + " is the favorite to win by " + winBy;
        }else{
            String winBy = "";
            if(fighter2.getWeight() > fighter1.getWeight()){
                winBy = "knockout";
            }else if(fighter2.getAge() > fighter1.getAge()){
                winBy = "split decision";
            }else if(height2 > height1){
                winBy = "tko";
            }
            return fighter2.getName() + " is the favorite to win by " + winBy;
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
        if(result.toLowerCase().equals("decision")){
            return DECISION;
        }else if(result.toLowerCase().equals("split decision")){
            return SPLIT_DECISION;
        }else if(result.toLowerCase().equals("ko")){
            return KO;
        }else if(result.toLowerCase().equals("tko")){
            return TKO;
        }else if(result.toLowerCase().equals("draw")){
            return DRAW;
        }else if(result.toLowerCase().equals("doctor stoppage")){
            return DOCTOR_STOPPAGE;
        }else if(result.toLowerCase().equals("tba")){
            return TBA;
        }else{
            return null;
        }
    }
    public FightType getFightType(String fightType){
        if(fightType.toLowerCase().equals("three rounds")){
            return THREE_ROUNDS;
        }else if(fightType.toLowerCase().equals("five rounds")){
            return FIVE_ROUNDS;
        }else if(fightType.toLowerCase().equals("championship")){
            return CHAMPIONSHIP;
        }else{
            return null;
        }
    }
}
