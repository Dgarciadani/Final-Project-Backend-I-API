package com.grego.Final_Project_Refactor_clase24.repository;

import com.grego.Final_Project_Refactor_clase24.domain.Appointment;
import com.grego.Final_Project_Refactor_clase24.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("select a from Appointment a where a.patient.id = ?1")
    List<Appointment> findByPatientId(int id);

    @Query("select a from Appointment a where a.dentist.id = ?1")
    List<Appointment> findByDentistId(int id);

    //DELETE  ALL APPOINTMENTS WHERE ENTITY ID =
    @Transactional
    @Modifying
    @Query("delete from Appointment a where a.patient.id = ?1")
    void deleteByPatientId(Integer id);

    @Transactional
    @Modifying
    @Query("delete from Appointment a where a.dentist.id = ?1")
    void deleteByDentistId(Integer id);

}