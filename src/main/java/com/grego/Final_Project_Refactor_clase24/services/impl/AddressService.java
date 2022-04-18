package com.grego.Final_Project_Refactor_clase24.services.impl;

import com.grego.Final_Project_Refactor_clase24.domain.Address;
import com.grego.Final_Project_Refactor_clase24.dto.AddressDTO;
import com.grego.Final_Project_Refactor_clase24.repository.AddressRepository;
import com.grego.Final_Project_Refactor_clase24.services.IAddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AddressDTO findById(Integer id) {
        return addressRepository.findById(id).map(address -> mapToDTO(address)).orElse(null);
    }

    @Override
    public AddressDTO save(AddressDTO entity) {
        Address address = mapToEntity(entity);
        address = addressRepository.save(address);
        return mapToDTO(address);
    }

    @Override
    public void deleteById(Integer id) {
        //WTF UNA ARROW FUNCTION
        addressRepository.findById(id).ifPresent(address -> addressRepository.deleteById(id));
    }

    @Override
    public AddressDTO update(Integer id, AddressDTO entity) {
        Address address = mapToEntity(entity);
        address.setAddressId(id);
        Address newAddress = addressRepository.save(address);
        return mapToDTO(newAddress);
    }

    @Override
    public List<AddressDTO> findAll() {
        return addressRepository.findAll().stream().map(address -> mapToDTO(address)).collect(java.util.stream.Collectors.toList());
    }


    //------Mapper-----
    private AddressDTO mapToDTO(Address address) {
        return modelMapper.map(address, AddressDTO.class);
    }

    private Address mapToEntity(AddressDTO addressDTO) {
        return modelMapper.map(addressDTO, Address.class);
    }
}
