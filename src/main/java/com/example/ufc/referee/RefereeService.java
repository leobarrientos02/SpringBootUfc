package com.example.ufc.referee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class RefereeService {

    private final RefereeRepository refereeRepository;

    @Autowired
    public RefereeService(RefereeRepository refereeRepository) {
        this.refereeRepository = refereeRepository;
    }

    public List<Referee> getAllReferees() {
        return refereeRepository.findAll();
    }

    public void addNewReferee(Referee referee) {
        Optional<Referee> refereeOptional = refereeRepository.findRefereeByName(referee.getName());
        if(refereeOptional.isPresent()){
            throw new IllegalStateException("Referee is already registered");
        }
        refereeRepository.save(referee);
    }
    public void deleteReferee(Long refereeId) {
        boolean exists = refereeRepository.existsById(refereeId);
        if(!exists){
            throw new IllegalStateException("Referee with the id of " + refereeId + " does not exists.");
        }else{
            refereeRepository.deleteById(refereeId);
        }
    }

    @Transactional
    public void updateReferee(Long refereeId, RefereeDto dto) {
        Referee referee = refereeRepository.findById(refereeId)
                .orElseThrow(()->
                        new IllegalStateException("Referee with the id of " + refereeId + " does not exists."));

        if(dto.getAge() == null){
            throw new IllegalStateException("Please enter a valid age");
        }else if(dto.getAge() < 18){
            throw new IllegalStateException("You must be an adult to be a referee");
        }else{
            referee.setAge(dto.getAge());
        }

        if(dto.getDob() == null){
            throw new IllegalStateException("Please enter a valid date of birth");
        }else{
            referee.setDob(dto.getDob());
        }
        if(dto.getName() != null && dto.getName().length() > 0){
            referee.setName(dto.getName());
        }else{
            throw new IllegalStateException("Please enter a valid name");
        }
    }
}
