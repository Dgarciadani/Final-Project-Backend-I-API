package com.grego.Final_Project_Refactor_clase24.controller;

import com.grego.Final_Project_Refactor_clase24.dto.AddressDTO;
import com.grego.Final_Project_Refactor_clase24.services.impl.AddressService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@CrossOrigin(origins = "*")
public class AddressController {

private final static Logger logger = org.apache.logging.log4j.LogManager.getLogger(AddressController.class);


    @Autowired
    private AddressService addressService;

    @GetMapping("/id={id}")
    public ResponseEntity<AddressDTO> getAddress(@PathVariable Integer id) {
        logger.info("Getting address with id: " + id);
        return ResponseEntity.ok(addressService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO addressDTO) {
        logger.info("Adding address: " + addressDTO.getStreet()+ addressDTO.getDoor());
        return ResponseEntity.ok(addressService.save(addressDTO));
    }

    @PutMapping("/id={id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Integer id, @RequestBody AddressDTO addressDTO) {
        logger.info("Updating address with id: " + id);
        return ResponseEntity.ok(addressService.update(id, addressDTO));
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Integer id) {
        addressService.deleteById(id);
        logger.info("Deleting address with id: " + id);
        return ResponseEntity.ok().body("Address deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<AddressDTO>> getAllAddress() {
        logger.info("Getting all addresses");
        return ResponseEntity.ok(addressService.findAll());
    }
}




