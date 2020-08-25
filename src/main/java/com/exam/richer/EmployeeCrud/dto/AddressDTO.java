package com.exam.richer.EmployeeCrud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

    private String endereco;
    private String bairro;
    private String cidade;
    private String uf;

}
