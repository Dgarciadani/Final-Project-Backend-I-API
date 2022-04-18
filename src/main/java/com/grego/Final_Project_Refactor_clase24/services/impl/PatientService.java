package com.grego.Final_Project_Refactor_clase24.services.impl;

import com.grego.Final_Project_Refactor_clase24.domain.Patient;
import com.grego.Final_Project_Refactor_clase24.dto.PatientDTO;
import com.grego.Final_Project_Refactor_clase24.exceptions.ResourceNotFoundException;
import com.grego.Final_Project_Refactor_clase24.repository.AppointmentRepository;
import com.grego.Final_Project_Refactor_clase24.repository.PatientRepository;
import com.grego.Final_Project_Refactor_clase24.services.IPatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService implements IPatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ModelMapper modelmapper;

    @Override
    public PatientDTO findById(Integer id) {
       // return patientRepository.findById(id).map(this::mapToDTO).orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
        return patientRepository.findById(id).map(this::mapToDTO).orElse(null);
    }

    @Override
    public PatientDTO save(PatientDTO entity) {
        Patient patient = mapToEntity(entity);
        patient = patientRepository.save(patient);
        return mapToDTO(patient);
    }

    @Override
    public void deleteById(Integer id) {
        patientRepository.findById(id).ifPresent(patientRepository::delete);
    }

    @Override
    public PatientDTO update(Integer id, PatientDTO entity) {
        Patient patient = mapToEntity(entity);
        patient.setPatient_id(id);
        patient = patientRepository.save(patient);
        return mapToDTO(patient);
    }

    @Override
    public List<PatientDTO> findAll() {
        return patientRepository.findAll().stream().map(this::mapToDTO).collect(java.util.stream.Collectors.toList());
    }

    public List<PatientDTO> findByName(String name) {
        return patientRepository.findByName(name).stream().map(this::mapToDTO).collect(java.util.stream.Collectors.toList());
    }

    public PatientDTO findByEmail(String email) {
        return patientRepository.findByEmail(email).map(this::mapToDTO).orElse(null);
    }

    //-----Mapper-----
    public PatientDTO mapToDTO(Patient patient) {
        return modelmapper.map(patient, PatientDTO.class);
    }

    public Patient mapToEntity(PatientDTO patientDTO) {
        return modelmapper.map(patientDTO, Patient.class);
    }


}
