package com.grego.Final_Project_Refactor_clase24.repository;

import com.grego.Final_Project_Refactor_clase24.domain.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, Integer> {

}
