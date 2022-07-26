package com.example.ufc.fighter;

import com.example.ufc.sharedResources.Weightclass;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


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

    @GetMapping(path="{fighterId}")
    public Fighter getFighterById(@PathVariable("fighterId") Long fighterId){
        Optional<Fighter> fighter = fighterService.getFighterById(fighterId);
        return fighter.orElse(null);
    }
    @PostMapping
    public void registerNewFighter(@RequestBody FighterDto dto){
        Fighter fighter = new Fighter();
        fighter.setName(dto.getName());
        fighter.setAge(dto.getAge());
        fighter.setDob(dto.getDob());
        fighter.setWeight(dto.getWeight());
        fighter.setHeight(dto.getHeight());
        fighter.setReach(dto.getReach());
        fighter.setFightingOutOf(dto.getFightingOutOf());
        fighter.setDebut(dto.getDebut());
        fighter.setImageUrl(dto.getImageUrl());
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
