package com.grego.Final_Project_Refactor_clase24;

import com.grego.Final_Project_Refactor_clase24.dto.AddressDTO;
import com.grego.Final_Project_Refactor_clase24.dto.AppointmentDTO;
import com.grego.Final_Project_Refactor_clase24.dto.DentistDTO;
import com.grego.Final_Project_Refactor_clase24.dto.PatientDTO;
import com.grego.Final_Project_Refactor_clase24.exceptions.ResourceNotFoundException;
import com.grego.Final_Project_Refactor_clase24.services.impl.AppointmentService;
import com.grego.Final_Project_Refactor_clase24.services.impl.DentistService;
import com.grego.Final_Project_Refactor_clase24.services.impl.PatientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FinalProjectRefactorClase24ApplicationTests {

    @Autowired
    DentistService dentistService;
    @Autowired
    PatientService patientService;
    @Autowired
    AppointmentService appointmentService;

    @Test
    public void saveDentist() {
        DentistDTO dentistDTO = new DentistDTO();
        dentistDTO.setName("Dentist");
        dentistDTO.setLastName("Dentist");
        dentistDTO.setRegister(465465);


        Assertions.assertNotNull(dentistService.save(dentistDTO).getDentist_id());

    }

    @Test
    public void savePatient() {
        PatientDTO patientdto = new PatientDTO();
        patientdto.setName("Pedro");
        patientdto.setLastName("Perez");
        AddressDTO address = new AddressDTO();
        address.setCity("Ciudad");
        address.setStreet("Calle");
        address.setDoor(123);
        address.setState("Estado");
        patientdto.setAddress(address);
        patientdto.setDni(123456789);
        patientdto.setDateInit(new java.sql.Date(new java.util.Date().getTime()));
        Assertions.assertNotNull(patientService.save(patientdto).getPatient_id());
    }

    @Test
    public void saveAppointment() {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        PatientDTO patient = new PatientDTO();
        patient.setPatient_id(1);
        DentistDTO dentist = new DentistDTO();
        dentist.setDentist_id(1);
        appointmentDTO.setPatient(patient);
        appointmentDTO.setDentist(dentist);
        appointmentDTO.setDate(new java.sql.Date(new java.util.Date().getTime()));
        Assertions.assertNotNull(appointmentService.save(appointmentDTO).getAppointment_id());
    }

    @Test
    public void getAppointment() {
        Assertions.assertNotNull(appointmentService.findById(1));
    }

    @Test
    public void getPatient() throws ResourceNotFoundException {
        Assertions.assertNotNull(patientService.findById(1));
    }

    @Test
    public void getDentist() throws ResourceNotFoundException {
        Assertions.assertNotNull(dentistService.findById(1));
    }

    @Test
    public void getAllAppointments() {
        Assertions.assertNotNull(appointmentService.findAll());
    }

    @Test
    public void getAllPatients() {
        Assertions.assertNotNull(patientService.findAll());
    }

    @Test
    public void getAllDentists() {
        Assertions.assertNotNull(dentistService.findAll());
    }

    @Test
    public void updateAppointment() {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        PatientDTO patient = new PatientDTO();
        patient.setPatient_id(1);
        DentistDTO dentist = new DentistDTO();
        dentist.setDentist_id(3);
        appointmentDTO.setPatient(patient);
        appointmentDTO.setDentist(dentist);
        appointmentDTO.setDate(new java.sql.Date(new java.util.Date().getTime()));
        Assertions.assertNotNull(appointmentService.update(1, appointmentDTO).getDentist().getDentist_id());
    }

    @Test
    public void updatePatient() throws ResourceNotFoundException {
        PatientDTO patientdto = new PatientDTO();
        patientdto.setName("Pedro");
        patientdto.setLastName("Perez");
        AddressDTO address = new AddressDTO();
        address.setCity("Ciudad");
        address.setStreet("Calle");
        address.setDoor(123);
        address.setState("Estado");
        patientdto.setAddress(address);
        patientdto.setDni(123456789);
        patientdto.setDateInit(new java.sql.Date(new java.util.Date().getTime()));
        Assertions.assertEquals(patientService.update(2, patientdto).getAddress().getCity(), "Ciudad");
    }

    @Test
    public void updateDentist() {
        DentistDTO dentistDTO = new DentistDTO();
        dentistDTO.setName("Dentist");
        dentistDTO.setLastName("Dentist");
        dentistDTO.setRegister(6151);
        Assertions.assertEquals(dentistService.update(2, dentistDTO).getRegister(), 6151);
    }

    /*    @Test
        public void deleteAppointment() {
        }*/
    @Test
    public void deletePatient() throws ResourceNotFoundException {
        patientService.deleteById(2);
        Assertions.assertNull(patientService.findById(2));
    }
}
