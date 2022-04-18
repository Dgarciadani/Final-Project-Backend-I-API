package com.grego.Final_Project_Refactor_clase24.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grego.Final_Project_Refactor_clase24.domain.Appointment;
import com.grego.Final_Project_Refactor_clase24.dto.AppointmentDTO;
import com.grego.Final_Project_Refactor_clase24.repository.AppointmentRepository;
import com.grego.Final_Project_Refactor_clase24.services.IAppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService implements IAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ModelMapper modelmapper;


    @Override
    public AppointmentDTO findById(Integer id) {
        return appointmentRepository.findById(id).map(this::mapToDTO).orElse(null);
    }

    @Override
    public AppointmentDTO save(AppointmentDTO entity) {
        Appointment appointment = mapToEntity(entity);
        appointment = appointmentRepository.save(appointment);
        return mapToDTO(appointment);
    }

    @Override
    public void deleteById(Integer id) {
        appointmentRepository.findById(id).ifPresent(appointment -> appointmentRepository.delete(appointment));
    }

    @Override
    public AppointmentDTO update(Integer id, AppointmentDTO entity) {
        Appointment appointment = mapToEntity(entity);
        appointment.setAppointment_id(id);
        appointment = appointmentRepository.save(appointment);
        return mapToDTO(appointment);
    }

    @Override
    public List<AppointmentDTO> findAll() {
        return appointmentRepository.findAll().stream().map(this::mapToDTO).collect(java.util.stream.Collectors.toList());
    }
    public List<AppointmentDTO> findByPatientId(Integer id){
        return appointmentRepository.findByPatientId(id).stream().map(this::mapToDTO).collect(java.util.stream.Collectors.toList());
    }
    public List<AppointmentDTO> findByDentistId(Integer id){
        return appointmentRepository.findByDentistId(id).stream().map(this::mapToDTO).collect(java.util.stream.Collectors.toList());
    }
    public void deleteByPatientId(Integer id){
        appointmentRepository.deleteByPatientId(id);
    }
    public void deleteByDentistId(Integer id){
        appointmentRepository.deleteByDentistId(id);
    }


    //----Mapper----
    public AppointmentDTO mapToDTO(Appointment appointment) {
        return modelmapper.map(appointment, AppointmentDTO.class);
    }

    public Appointment mapToEntity(AppointmentDTO appointmentDTO) {
        return modelmapper.map(appointmentDTO, Appointment.class);
    }
}
