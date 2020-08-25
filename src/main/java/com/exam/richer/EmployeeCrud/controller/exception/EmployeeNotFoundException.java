package com.exam.richer.EmployeeCrud.controller.exception;

public class EmployeeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 6538710215598916268L;

    public EmployeeNotFoundException(String mensagem) {
        super(mensagem);
    }
}