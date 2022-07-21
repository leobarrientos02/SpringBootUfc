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
import java.util.ArrayList;
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

        Fighter bet = this.finalPrediction(fighter1.get(), fighter2.get());

        fight.setDescription(
                fighter1.get().getName() + " is set to fight " +
                        fighter2.get().getName() +  " in a " +
                        fighter1.get().getWeightclass() + " " +
                        this.getFightType(dto.getFightType()) + " fight in the " +
                        dto.getLocation() + " at " +
                        dto.getDate().getMonth() + " " + dto.getDate().getDayOfMonth() + " " +
                        dto.getDate().getYear() + " with " +
                        referee.get().getName() + " as the referee. Looking at the betting odds " +
                        bet.getName() + " is the favorite to win by " +
                        this.getResult(dto.getResult())
        );

        if(dto.getLocation() == null){
            throw new IllegalStateException("Please enter a location");
        }else{
            fight.setLocation(dto.getLocation());
        }

        if(dto.getResult() == null){
            throw new IllegalStateException("Please enter the result");
        }else{
            fight.setResult(this.getResult(dto.getResult()));
        }

        if(dto.getFightType() == null){
            throw new IllegalStateException("Please enter a fight type");
        }else{
            fight.setFightType(this.getFightType(dto.getFightType()));
        }

        fightRepository.save(fight);

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

    public Result getResult(String result){
        if(result.toLowerCase().equals("win ko")){
            return WIN_KO;
        }else if(result.toLowerCase().equals("win split decision")){
            return WIN_SPLIT_DECISION;
        }else if(result.toLowerCase().equals("win decision")){
            return WIN_DECISION;
        }else if( result.toLowerCase().equals("win tko")){
            return WIN_TKO;
        }else if(result.toLowerCase().equals("win disqualification")){
            return WIN_DISQUALIFICATION;
        }else if(result.toLowerCase().equals("lose ko")){
            return LOSE_KO;
        }else if(result.toLowerCase().equals("lose split decision")){
            return LOSE_SPLIT_DECISION;
        }else if(result.toLowerCase().equals("lose decision")){
            return LOSE_DECISION;
        }else if(result.toLowerCase().equals("lose tko")){
            return LOSE_TKO;
        }else if(result.toLowerCase().equals("lose disqualification")){
            return LOSE_DISQUALIFICATION;
        }else if(result.toLowerCase().equals("decision")){
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
        }else{
            return null;
        }
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

        Fighter bet = this.finalPrediction(fighter1.get(), fighter2.get());

        fight.setDescription(
                fighter1.get().getName() + " is set to fight " +
                        fighter2.get().getName() +  " in a " +
                        fighter1.get().getWeightclass() + " " +
                        this.getFightType(dto.getFightType()) + " fight in the " +
                        dto.getLocation() + " at " +
                        dto.getDate().getMonth() + " " + dto.getDate().getDayOfMonth() + " " +
                        dto.getDate().getYear() + " with " +
                        referee.get().getName() + " as the referee. Looking at the betting odds " +
                        bet.getName() + " is the favorite to win by " +
                        this.getResult(dto.getResult())
        );

        if(dto.getLocation() == null){
            throw new IllegalStateException("Please enter a location");
        }else{
            fight.setLocation(dto.getLocation());
        }

        if(dto.getResult() == null){
            throw new IllegalStateException("Please enter the result");
        }else{
            fight.setResult(this.getResult(dto.getResult()));
        }

        if(dto.getFightType() == null){
            throw new IllegalStateException("Please enter a fight type");
        }else{
            fight.setFightType(this.getFightType(dto.getFightType()));
        }
    }

    public Fighter finalPrediction(Fighter fighter1, Fighter fighter2){
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
        if(height1 < height2){
            fighter1Advantage = fighter1Advantage  + 1;
        }else{
            fighter2Advantage = fighter2Advantage  + 1;
        }

        if(fighter1Advantage < fighter2Advantage){
            return fighter1;
        }else{
            return fighter2;
        }
    }
}
