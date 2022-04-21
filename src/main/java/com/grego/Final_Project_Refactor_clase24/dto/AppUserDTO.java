package com.grego.Final_Project_Refactor_clase24.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppUserDTO {

    private String email;
    private String name;
    private String username;
    private String password;


}
