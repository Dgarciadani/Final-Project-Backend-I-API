package com.grego.Final_Project_Refactor_clase24.services.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.grego.Final_Project_Refactor_clase24.domain.Address;
import com.grego.Final_Project_Refactor_clase24.domain.Dentist;
import com.grego.Final_Project_Refactor_clase24.dto.DentistDTO;
import com.grego.Final_Project_Refactor_clase24.repository.DentistRepository;
import com.grego.Final_Project_Refactor_clase24.services.IDentistService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistService implements IDentistService {

    @Autowired
    private DentistRepository dentistRepository;

    @Autowired
    private ModelMapper modelmapper;

    @Override
    public DentistDTO findById(Integer id) {
        return dentistRepository.findById(id).map(this::mapToDTO).orElse(null);
    }

    @Override
    public DentistDTO save(DentistDTO entity) {
        Dentist dentist = mapToEntity(entity);
        dentist = dentistRepository.save(dentist);
        return mapToDTO(dentist);
    }

    @Override
    public void deleteById(Integer id) {
        dentistRepository.findById(id).ifPresent(dentist -> dentistRepository.delete(dentist));
    }

    @Override
    public DentistDTO update(Integer id, DentistDTO entity) {
        Dentist dentist = mapToEntity(entity);
        dentist.setDentist_id(id);
        dentist = dentistRepository.save(dentist);
        return mapToDTO(dentist);
    }

    @Override
    public List<DentistDTO> findAll() {
        return dentistRepository.findAll().stream().map(this::mapToDTO).collect(java.util.stream.Collectors.toList());
    }

    //-----Maper-----
    private DentistDTO mapToDTO(Dentist entity) {
        return modelmapper.map(entity, DentistDTO.class);
    }

    private Dentist mapToEntity(DentistDTO dto) {
        return modelmapper.map(dto, Dentist.class);
    }
}
