package com.exam.richer.EmployeeCrud.service;

import com.exam.richer.EmployeeCrud.domain.EmployeeDomain;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    public EmployeeDomain createEmployee(EmployeeDomain employeeDomain);

    public void deleteEmployee(String email);

    public void changeAddressEmployee(String emailEmployee, String address);

    public EmployeeDomain changeEmployee(String email, EmployeeDomain employeeDomain);

    public List<EmployeeDomain> listEmployee();

}
