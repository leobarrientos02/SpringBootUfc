package com.example.ufc.fighter;

import com.example.ufc.fight.Fight;
import com.example.ufc.referee.Referee;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.ufc.sharedResources.FightType.CHAMPIONSHIP;
import static com.example.ufc.sharedResources.Result.DECISION;
import static com.example.ufc.sharedResources.Weightclass.Featherweight;
import static com.example.ufc.sharedResources.Weightclass.Welterweight;

@RestController
@RequestMapping(path = "api/v1/fighters")
public class FighterController {

    private final FighterService fighterService;

    public FighterController(FighterService fighterService) {
        this.fighterService = fighterService;
    }

    @GetMapping
    public List<Fighter> getAllFighters() {
        return fighterService.getAllFighters();
    }

    @PostMapping
    public void registerNewFighter(@RequestBody FighterDto dto){
        Fighter fighter = new Fighter();
        fighter.setName(dto.getName());
        fighter.setAge(dto.getAge());
        fighter.setDob(dto.getDob());
        fighter.setHeight(dto.getHeight());
        fighter.setWeight(dto.getWeight());
        fighter.setWeightclass(fighterService.getWeightclass(dto.getWeight()));
        fighterService.addNewFighter(fighter);
    }

    @DeleteMapping(path = "{fighterId}")
    public void deleteFighter(@PathVariable("fighterId") Long fighterId){
        fighterService.deleteFighter(fighterId);
    }

    @PutMapping(path = "{fighterId}")
    public void updateFighter(
            @PathVariable("fighterId") Long fighterId,
            @RequestBody FighterDto dto){
        fighterService.updateFighter(fighterId, dto);
    }
}
