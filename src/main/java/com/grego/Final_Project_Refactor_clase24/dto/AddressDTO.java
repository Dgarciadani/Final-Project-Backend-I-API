package com.grego.Final_Project_Refactor_clase24.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDTO {


    private String street;
    private Integer door;
    private String city;
    private String state;
    @JsonIgnore
    private PatientDTO patient;

    public AddressDTO() {
    }

    public AddressDTO(String street, Integer door, String city, String state) {
        this.street = street;
        this.door = door;
        this.city = city;
        this.state = state;
    }
}
