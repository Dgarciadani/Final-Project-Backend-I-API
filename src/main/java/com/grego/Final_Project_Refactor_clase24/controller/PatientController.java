package com.grego.Final_Project_Refactor_clase24.controller;


import com.grego.Final_Project_Refactor_clase24.dto.PatientDTO;
import com.grego.Final_Project_Refactor_clase24.exceptions.ResourceNotFoundException;
import com.grego.Final_Project_Refactor_clase24.services.impl.PatientService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class PatientController {
    private final static Logger logger = org.apache.logging.log4j.LogManager.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;


    @GetMapping("/id={id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable Integer id) throws ResourceNotFoundException {
        if (patientService.findById(id) != null) {
            logger.info("Patient id: " + id);
            return ResponseEntity.ok(patientService.findById(id));
        } else {
            logger.info("Patient id: " + id + " not found");
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/add")
    public ResponseEntity<PatientDTO> savePatient(@RequestBody PatientDTO patientDTO) {
        logger.info("Adding Patient: " + patientDTO);
        return ResponseEntity.ok(patientService.save(patientDTO));
    }

    @PutMapping("/id={id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Integer id, @RequestBody PatientDTO patientDTO) throws ResourceNotFoundException {
        logger.info("Updating Patient: " + id);
        return ResponseEntity.ok(patientService.update(id, patientDTO));
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity<String> deletePatient(@PathVariable Integer id) {
        patientService.deleteById(id);
        logger.info("Deleting Patient: " + id);
        return ResponseEntity.ok().body("Patient deleted");

    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<PatientDTO>> findAll() {
        logger.info("Getting all patients");
        return ResponseEntity.ok(patientService.findAll());
    }

    @GetMapping("/name={name}")
    public ResponseEntity<Iterable<PatientDTO>> findByName(@PathVariable String name) {
        logger.info("Getting all patients with name: " + name);
        return ResponseEntity.ok(patientService.findByName(name));
    }

    @GetMapping("/email={email}")
    public ResponseEntity<PatientDTO> findByEmail(@PathVariable String email) {
        logger.info("Getting patient with email: " + email);
        return ResponseEntity.ok(patientService.findByEmail(email));
    }
}

