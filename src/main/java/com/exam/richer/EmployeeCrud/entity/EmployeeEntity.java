package com.exam.richer.EmployeeCrud.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String cep;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address")
    private AddressEntity address;

}
