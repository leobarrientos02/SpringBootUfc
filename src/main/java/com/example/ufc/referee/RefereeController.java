package com.example.ufc.referee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public void addNewReferee(@RequestBody RefereeDto dto) {
        Referee referee = new Referee();
        referee.setName(dto.getName());
        referee.setAge(dto.getAge());
        referee.setDob(dto.getDob());
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
