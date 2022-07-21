package com.example.ufc.fight;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/fight")
public class FightController {

    private final FightService fightService;

    public FightController(FightService fightService) {
        this.fightService = fightService;
    }

    @GetMapping
    public List<Fight> getAllFights() {
        return fightService.getAllFights();
    }

    @PostMapping
    public void addFight(@RequestBody FightDto dto){
        fightService.addFight(dto);
    }

    @DeleteMapping(path = "{fightId}")
    public void deleteFight(@PathVariable("fightId") Long fightId){
        fightService.deleteFight(fightId);
    }

    @PutMapping(path = "{fightId}")
    public void updateFight(
            @PathVariable("fightId") Long fightId,
            @RequestBody FightDto dto){
        fightService.updateFight(fightId, dto);
    }

}
