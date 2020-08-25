package com.exam.richer.EmployeeCrud.service;

import com.exam.richer.EmployeeCrud.dto.AddressDTO;
import com.exam.richer.EmployeeCrud.service.fallback.AddressAPIFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="AddressService", url = "https://webmaniabr.com/api/1/cep/", fallback = AddressAPIFallback.class)
public interface AddressService {

    @GetMapping("/{cep}")
    public ResponseEntity<AddressDTO> getAddress(@PathVariable("cep") String cep, @RequestParam String app_key, @RequestParam String app_secret);

}
