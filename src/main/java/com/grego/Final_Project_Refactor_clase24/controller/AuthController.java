package com.grego.Final_Project_Refactor_clase24.controller;

import com.grego.Final_Project_Refactor_clase24.dto.AppUserDTO;
import com.grego.Final_Project_Refactor_clase24.services.impl.login.AppUserService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final static Logger logger = org.apache.logging.log4j.LogManager.getLogger(AddressController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AppUserService appUserService;


    @PostMapping("/login")
    public ResponseEntity<String> authByPost(@RequestBody AppUserDTO appUserDTO) {
        try {
            logger.info("authenticating");
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(appUserDTO.getUsername(), appUserDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(auth);
            logger.info("authenticated");
            return new ResponseEntity<>("Log ok", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("authentication failed");
            return new ResponseEntity<>("Log failed", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerByPost(@RequestBody AppUserDTO appUserDTO) {

        try {
            logger.info("registering");
            appUserService.registerUser(appUserDTO);
            logger.info("registered");
            return new ResponseEntity<>("Register ok", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("register failed");
            return new ResponseEntity<>("Register failed", HttpStatus.UNAUTHORIZED);
        }
    }
}
