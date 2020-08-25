package com.exam.richer.EmployeeCrud.mapper;

import com.exam.richer.EmployeeCrud.domain.EmployeeDomain;
import com.exam.richer.EmployeeCrud.dto.EmployeeDTO;
import com.exam.richer.EmployeeCrud.dto.EmployeeDTOAddress;
import com.exam.richer.EmployeeCrud.entity.EmployeeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDomain toEmployeeDomain(EmployeeDTO employeeDTO);

    EmployeeDomain toEmployeeDomain(EmployeeEntity employeeEntity);

    EmployeeDTO toEmployeeDto(EmployeeDomain employeeDomain);

    EmployeeDTOAddress toEmployeeDtoAddress(EmployeeDomain employeeDomain);

    EmployeeEntity toEmployeeEntity (EmployeeDomain employeeDomain);

    List<EmployeeDomain> toListEmployeeDomain(List<EmployeeEntity> listEmployeeEntity);

    List<EmployeeDTOAddress> toListEmployeeDTO(List<EmployeeDomain> listEmployeeDomain);

}
