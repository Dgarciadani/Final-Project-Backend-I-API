package com.grego.Final_Project_Refactor_clase24.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grego.Final_Project_Refactor_clase24.domain.Appointment;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DentistDTO {

    private Integer dentist_id;
    private String name;
    private String lastName;

    private Integer register;


    public DentistDTO() {
    }

}
