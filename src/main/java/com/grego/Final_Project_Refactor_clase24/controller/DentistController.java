package com.grego.Final_Project_Refactor_clase24.controller;


import com.grego.Final_Project_Refactor_clase24.domain.Dentist;
import com.grego.Final_Project_Refactor_clase24.dto.DentistDTO;
import com.grego.Final_Project_Refactor_clase24.exceptions.ResourceNotFoundException;
import com.grego.Final_Project_Refactor_clase24.services.IDentistService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/dentist")
@CrossOrigin(origins = "*")
public class DentistController {
    private final static Logger logger = org.apache.logging.log4j.LogManager.getLogger(DentistController.class);

    @Autowired
    private IDentistService dentistService;

    @GetMapping("/id={id}")
    public ResponseEntity<DentistDTO> findById(@PathVariable Integer id) throws ResourceNotFoundException {
        if (dentistService.findById(id) == null) {
            logger.info("Dentist with id: " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("find Dentist by id"+ id);
        return ResponseEntity.ok().body(dentistService.findById(id));
    }
    @PostMapping("/add")
    public ResponseEntity<DentistDTO> addDentist(@Valid @RequestBody DentistDTO dentistDTO) {
        logger.info("add Dentist");
        return ResponseEntity.ok().body(dentistService.save(dentistDTO));
    }

    @PutMapping("id={id}")
    public ResponseEntity<DentistDTO> updateDentist(@PathVariable Integer id, @Valid @RequestBody DentistDTO dentistDTO) throws ResourceNotFoundException {
        logger.info("update Dentist with id: " + id);
        return ResponseEntity.ok().body(dentistService.update(id, dentistDTO));
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity<String> deleteDentist(@PathVariable Integer id) throws ResourceNotFoundException {
        dentistService.deleteById(id);
        logger.info("deleted Dentist with id: " + id);
        return ResponseEntity.ok().body("Dentist deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<DentistDTO>> getAllDentists() {
        logger.info("get all Dentists");
        return ResponseEntity.ok().body(dentistService.findAll());
    }
}








