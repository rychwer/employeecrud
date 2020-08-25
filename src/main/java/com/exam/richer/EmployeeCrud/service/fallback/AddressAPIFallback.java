package com.exam.richer.EmployeeCrud.service.fallback;

import com.exam.richer.EmployeeCrud.dto.AddressDTO;
import com.exam.richer.EmployeeCrud.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressAPIFallback implements AddressService {

    @Override
    public ResponseEntity<AddressDTO> getAddress(String cep, String app_key, String app_secret) {
        return new ResponseEntity<AddressDTO>(new AddressDTO(), HttpStatus.OK);
    }
}
