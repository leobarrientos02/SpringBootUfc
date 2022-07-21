package com.example.ufc.fighter;

import com.example.ufc.sharedResources.Weightclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.example.ufc.sharedResources.Weightclass.*;
@Service
public class FighterService {

    private final FighterRepository fighterRepository;

    @Autowired
    public FighterService(FighterRepository fighterRepository) {
        this.fighterRepository = fighterRepository;
    }

    public List<Fighter> getAllFighters() {
        return fighterRepository.findAll();
    }

    public void addNewFighter(Fighter fighter) {
       Optional<Fighter> fighterOptional = fighterRepository
               .findFighterByName(fighter.getName());
       if(fighterOptional.isPresent()){
           throw new IllegalStateException("Fighter already registered");
       }
       fighterRepository.save(fighter);
    }

    public Weightclass getWeightclass(Double weight){
        if(weight >= 206 && weight <= 265){
            return Heavyweight;
        }else if(weight >= 186 && weight <= 205){
            return Light_Heavyweight;
        }else if(weight >= 171 && weight <= 185){
            return Middleweight;
        }else if(weight >= 156 && weight <= 170){
            return Welterweight;
        }else if(weight >= 146 && weight <= 155){
            return Lightweight;
        }else if(weight >= 136 && weight <= 145){
            return Featherweight;
        }else if(weight >= 126 && weight <= 135){
            return Bantamweight;
        }else if(weight >= 116 && weight <= 125) {
            return Flyweight;
        }else{
            return Unknown;
        }
    }

    public void deleteFighter(Long fighterId) {
        boolean exists = fighterRepository.existsById(fighterId);
        if(!exists){
            throw new IllegalStateException("Fighter with the id of " + fighterId + " does not exist");
        }else{
            fighterRepository.deleteById(fighterId);
        }
    }

    @Transactional
    public void updateFighter(Long fighterId, FighterDto dto) {
        Fighter fighter = fighterRepository.findById(fighterId)
                .orElseThrow(()-> new IllegalStateException(
                        "Fighter with the id of " + fighterId + " does not exist"));

        if(dto.getName() != null && dto.getName().length() > 0){
            fighter.setName(dto.getName());
        }else{
            throw new IllegalStateException("Please enter a valid name");
        }

        if(dto.getAge() < 18){
            throw new IllegalStateException("We do not allow fighters under the age of 18");
        }else if(dto.getAge() == null){
            throw new IllegalStateException("Please enter age");
        }else if(dto.getAge() > 60){
            throw new IllegalStateException("We are sorry but we think you should retire.");
        }else{
            fighter.setAge(dto.getAge());
        }

        if(dto.getWeight() == null){
            throw new IllegalStateException("Please enter a weight to determine your weightclass");
        }else{
            fighter.setWeight(dto.getWeight());
            fighter.setWeightclass(this.getWeightclass(dto.getWeight()));
        }

        if(dto.getHeight() == null){
            throw new IllegalStateException("Please enter a height");
        }else{
            fighter.setHeight(dto.getHeight());
        }

        if(dto.getDob() == null){
            throw new IllegalStateException("Please enter date of birth");
        }else{
            fighter.setDob(dto.getDob());
        }
    }

    public Optional<Fighter> findFighterByName(String name){
        Optional<Fighter> fighter = fighterRepository.findFighterByName(name);

        if(!fighter.isPresent()){
            throw new IllegalStateException("Fighter was not found");
        }else{
            return fighter;
        }
    }
}
