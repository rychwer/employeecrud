package com.exam.richer.EmployeeCrud.service.impl;

import com.exam.richer.EmployeeCrud.controller.exception.AddresApiNotAvailableException;
import com.exam.richer.EmployeeCrud.controller.exception.EmployeeNotFoundException;
import com.exam.richer.EmployeeCrud.domain.EmployeeDomain;
import com.exam.richer.EmployeeCrud.dto.AddressDTO;
import com.exam.richer.EmployeeCrud.entity.EmployeeEntity;
import com.exam.richer.EmployeeCrud.mapper.AddressMapper;
import com.exam.richer.EmployeeCrud.mapper.EmployeeMapper;
import com.exam.richer.EmployeeCrud.repository.EmployeeRepository;
import com.exam.richer.EmployeeCrud.service.AddressService;
import com.exam.richer.EmployeeCrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    private final AddressService addressService;

    private final AddressMapper addressMapper;

    @Value("${api.service.address.appKey}")
    private String appKey;

    @Value("${api.service.address.appSecret}")
    private String appSecret;

    private static final Integer STATUS_CODE_OK = 200;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, AddressService addressService, AddressMapper addressMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.addressService = addressService;
        this.addressMapper = addressMapper;
    }


    @Override
    @Transactional
    public EmployeeDomain createEmployee(EmployeeDomain employeeDomain) {
        final Optional<EmployeeEntity> employeeRepositoryByEmail = this.employeeRepository.findByEmail(employeeDomain.getEmail());
        if(employeeRepositoryByEmail.isPresent()) {
            return employeeMapper.toEmployeeDomain(employeeRepositoryByEmail.get());
        }
        final ResponseEntity<AddressDTO> address = this.addressService.getAddress(employeeDomain.getCep(), appKey, appSecret);
        if(STATUS_CODE_OK == address.getStatusCode().value()){
            final EmployeeEntity employeeEntity = employeeMapper.toEmployeeEntity(employeeDomain);
            employeeEntity.setAddress(addressMapper.toAddressEntity(address.getBody()));
            return employeeMapper.toEmployeeDomain(employeeRepository.save(employeeEntity));
        } else {
            throw new AddresApiNotAvailableException("Address API is temporarily down. Please try again later");
        }
    }

    @Override
    @Transactional
    public void deleteEmployee(String email) {
        final Optional<EmployeeEntity> employeeOptional = employeeRepository.findByEmail(email);
        if(employeeOptional.isPresent()) {
            employeeRepository.delete(employeeOptional.get());
        } else {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }

    @Override
    @Transactional
    public void changeAddressEmployee(String emailEmployee, String cep){
        final Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findByEmail(emailEmployee);
        if(employeeEntityOptional.isPresent()) {
            final ResponseEntity<AddressDTO> address = this.addressService.getAddress(cep, appKey, appSecret);
            if(STATUS_CODE_OK == address.getStatusCode().value()) {
                final EmployeeEntity employeeEntity = employeeEntityOptional.get();
                employeeEntity.setAddress(addressMapper.toAddressEntity(address.getBody()));
                employeeEntity.setCep(cep);
                employeeRepository.save(employeeEntity);
            } else {
                throw new AddresApiNotAvailableException("Address API is temporarily down. Please try again later");
            }
        } else {
            throw new EmployeeNotFoundException("Employee not found");
        }

    }

    @Override
    @Transactional
    public EmployeeDomain changeEmployee(String email, EmployeeDomain employeeDomain) {
        final Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findByEmail(email);
        if(employeeEntityOptional.isPresent()) {
            final ResponseEntity<AddressDTO> address = this.addressService.getAddress(employeeDomain.getCep(), appKey, appSecret);
            if(STATUS_CODE_OK == address.getStatusCode().value()) {
                final EmployeeEntity employeeEntity = employeeEntityOptional.get();
                employeeEntity.setAddress(addressMapper.toAddressEntity(address.getBody()));
                employeeEntity.setCep(employeeDomain.getCep());
                employeeEntity.setName(employeeDomain.getName());
                return employeeMapper.toEmployeeDomain(employeeRepository.save(employeeEntity));
            } else {
                throw new AddresApiNotAvailableException("Address API is temporarily down. Please try again later");
            }
        } else {
            throw new EmployeeNotFoundException("Employee not found");
        }

    }

    @Override
    @Transactional
    public List<EmployeeDomain> listEmployee() {
        return employeeMapper.toListEmployeeDomain(employeeRepository.findAll());
    }
}
