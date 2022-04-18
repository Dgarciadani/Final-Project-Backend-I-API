package com.grego.Final_Project_Refactor_clase24.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;


@Setter
@Getter
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointment_id;

    @ManyToOne
    @JoinColumn(name = "dentist_id", nullable = false)
    private Dentist dentist;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    private Date date;
    private Double price;

    public Appointment() {
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointment_id=" + appointment_id +
                ", dentist=" + dentist +
                ", patient=" + patient +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(appointment_id, that.appointment_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointment_id);
    }
}
