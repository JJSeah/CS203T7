package com.example.electric.respository;

import com.example.electric.model.Appoinment;
import com.example.electric.service.AppoinmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppoinmentRepository extends JpaRepository<Appoinment, Long> {

}
