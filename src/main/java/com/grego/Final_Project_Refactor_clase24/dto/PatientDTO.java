package com.grego.Final_Project_Refactor_clase24.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grego.Final_Project_Refactor_clase24.domain.Appointment;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class PatientDTO {

    private Integer patient_id;
    private String name;
    private String lastName;
    private String email;
    private Integer dni;
    @OneToOne(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AddressDTO address;
    private Date dateInit;

    public PatientDTO() {
    }


}

