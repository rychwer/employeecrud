package com.exam.richer.EmployeeCrud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTOAddress {

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private AddressDTO address;

    @JsonProperty("email")
    private String email;

    @JsonProperty("cep")
    private String cep;

}
