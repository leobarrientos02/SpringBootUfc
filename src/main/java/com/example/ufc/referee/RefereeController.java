package com.example.ufc.referee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/referee")
public class RefereeController {

    private final RefereeService refereeService;

    @Autowired
    public RefereeController(RefereeService refereeService) {
        this.refereeService = refereeService;
    }

    @GetMapping
    public List<Referee> getAllReferees() {
        return refereeService.getAllReferees();
    }

    @GetMapping(path="{refereeId}")
    public Referee getReferee(@PathVariable("refereeId") Long refereeId){
        Optional<Referee> referee = refereeService.getRefereeById(refereeId);
        return referee.orElse(null);
    }

    @PostMapping
    public void addNewReferee(@RequestBody RefereeDto dto) {
        Referee referee = new Referee();
        referee.setName(dto.getName());
        referee.setAge(dto.getAge());
        referee.setDob(dto.getDob());
        referee.setImageUrl(dto.getImageUrl());
        refereeService.addNewReferee(referee);
    }

    @DeleteMapping(path = "{refereeId}")
    public void deleteReferee(@PathVariable("refereeId") Long refereeId) {
        refereeService.deleteReferee(refereeId);
    }

    @PutMapping(path = "{refereeId}")
    public void updateReferee(
            @PathVariable("refereeId") Long refereeId,
            @RequestBody RefereeDto dto){
        refereeService.updateReferee(refereeId, dto);
    }
}
