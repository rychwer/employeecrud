package com.exam.richer.EmployeeCrud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EmployeeDTO {

    @NotNull(message = "name cannot be null")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "email cannot be null")
    @JsonProperty("email")
    private String email;

    @NotNull(message = "cep cannot be null")
    @JsonProperty("cep")
    private String cep;

}
