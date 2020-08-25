package com.exam.richer.EmployeeCrud.controller.exception;

public class AddresApiNotAvailableException extends RuntimeException {

    private static final long serialVersionUID = 6538710215598916268L;

    public AddresApiNotAvailableException(String mensagem) {
        super(mensagem);
    }
}