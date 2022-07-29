package com.example.ufc.fighter;

import com.example.ufc.sharedResources.Weightclass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FighterRepository extends JpaRepository<Fighter, Long> {
    @Query("SELECT s FROM Fighter s WHERE s.name = ?1")
    Optional<Fighter> findFighterByName(String name);
}
