package com.exam.richer.EmployeeCrud.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDomain {

    private String name;

    private AddressDomain address;

    private String email;

    private String cep;

}
