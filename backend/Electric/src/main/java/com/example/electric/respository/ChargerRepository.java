package com.example.electric.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.electric.model.Charger;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargerRepository extends JpaRepository<Charger, Long> {

}