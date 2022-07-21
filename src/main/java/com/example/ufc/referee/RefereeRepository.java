package com.example.ufc.referee;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefereeRepository extends JpaRepository<Referee, Long> {
    @Query("SELECT s FROM Referee s WHERE s.name = ?1")
    Optional<Referee> findRefereeByName(String name);
}
