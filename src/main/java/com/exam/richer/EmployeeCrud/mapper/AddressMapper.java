package com.exam.richer.EmployeeCrud.mapper;

import com.exam.richer.EmployeeCrud.dto.AddressDTO;
import com.exam.richer.EmployeeCrud.entity.AddressEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressEntity toAddressEntity(AddressDTO addressDTO);

}
