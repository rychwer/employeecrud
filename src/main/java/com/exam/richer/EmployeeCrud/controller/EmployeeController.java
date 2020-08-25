package com.exam.richer.EmployeeCrud.controller;

import com.exam.richer.EmployeeCrud.dto.EmployeeDTO;
import com.exam.richer.EmployeeCrud.dto.EmployeeDTOAddress;
import com.exam.richer.EmployeeCrud.mapper.EmployeeMapper;
import com.exam.richer.EmployeeCrud.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/employee")
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<EmployeeDTOAddress> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<EmployeeDTOAddress>(employeeMapper.toEmployeeDtoAddress(employeeService.createEmployee(employeeMapper.toEmployeeDomain(employeeDTO))), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{emailEmployee}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<EmployeeDTOAddress> changeEmployee(@PathVariable String emailEmployee, @RequestBody @Valid EmployeeDTO employeeDTO) {
        return new ResponseEntity<EmployeeDTOAddress>(employeeMapper.toEmployeeDtoAddress(employeeService.changeEmployee(emailEmployee, employeeMapper.toEmployeeDomain(employeeDTO))), HttpStatus.OK);
    }

    @PatchMapping(path = "/{emailEmployee}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity changeAddressEmployee(@PathVariable String emailEmployee, @NotNull @RequestParam(name = "cep") String cep) {
        employeeService.changeAddressEmployee(emailEmployee, cep);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{emailEmployee}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity deleteEmployee(@PathVariable String emailEmployee) {
        employeeService.deleteEmployee(emailEmployee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List<EmployeeDTOAddress>> listEmployee() {
        return new ResponseEntity<List<EmployeeDTOAddress>>(employeeMapper.toListEmployeeDTO(employeeService.listEmployee()), HttpStatus.OK);
    }

}
