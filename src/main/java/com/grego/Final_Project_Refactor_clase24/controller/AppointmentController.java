package com.grego.Final_Project_Refactor_clase24.controller;


import com.grego.Final_Project_Refactor_clase24.dto.AppointmentDTO;
import com.grego.Final_Project_Refactor_clase24.services.impl.AppointmentService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final static Logger logger = org.apache.logging.log4j.LogManager.getLogger(AppointmentController.class);


    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/id={id}")
    public ResponseEntity<AppointmentDTO> findAppointmentById(@PathVariable Integer id) {
        logger.info("Getting appointment by id: " + id);
        if (appointmentService.findById(id) == null) {
            logger.error("Appointment not found");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(appointmentService.findById(id));

    }

    @PostMapping("/add")
    public ResponseEntity<AppointmentDTO> addAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        logger.info("Adding appointment: " + appointmentDTO.getDate()+ ", Patient: " + appointmentDTO.getPatient().getPatient_id() + ", Dentist: " + appointmentDTO.getDentist().getDentist_id());
        return ResponseEntity.ok(appointmentService.save(appointmentDTO));
    }

    @PutMapping("/id={id}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable Integer id, @RequestBody AppointmentDTO appointmentDTO) {
        logger.info("Updating appointment: " + appointmentDTO.getDate()+ ", Patient: " + appointmentDTO.getPatient().getPatient_id() + ", Dentist: " + appointmentDTO.getDentist().getDentist_id());
        return ResponseEntity.ok(appointmentService.update(id, appointmentDTO));
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Integer id) {
        logger.info("Deleting appointment: " + id);
        appointmentService.deleteById(id);
        return ResponseEntity.ok().body("Appointment deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<AppointmentDTO>> findAllAppointments() {
        logger.info("Getting all appointments");
        return ResponseEntity.ok(appointmentService.findAll());
    }

    @GetMapping("/dentist={id}")
    public ResponseEntity<Iterable<AppointmentDTO>> findAllAppointmentsByDentistId(@PathVariable Integer id) {
        logger.info("Getting all appointments by dentist id: " + id);
        return ResponseEntity.ok(appointmentService.findByDentistId(id));
    }

    @GetMapping("/patient={id}")
    public ResponseEntity<Iterable<AppointmentDTO>> findAllAppointmentsByPatientId(@PathVariable Integer id) {
        logger.info("Getting all appointments by patient id: " + id);
        return ResponseEntity.ok(appointmentService.findByPatientId(id));
    }

    @DeleteMapping("/dentist={id}")
    public ResponseEntity<String> deleteAppointmentByDentistId(@PathVariable Integer id) {
        logger.info("Deleting appointment by dentist id: " + id);
        appointmentService.deleteByDentistId(id);
        return ResponseEntity.ok().body("Appointments deleted");
    }
@DeleteMapping("/patient={id}")
    public ResponseEntity<String> deleteAppointmentByPatientId(@PathVariable Integer id) {
        logger.info("Deleting appointment by patient id: " + id);
        appointmentService.deleteByPatientId(id);
        return ResponseEntity.ok().body("Appointments deleted");
    }

}

