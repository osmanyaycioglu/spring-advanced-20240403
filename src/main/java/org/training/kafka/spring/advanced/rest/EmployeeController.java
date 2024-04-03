package org.training.kafka.spring.advanced.rest;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.kafka.spring.advanced.models.Employee;

@RestController
@RequestMapping("/api/v1/employee/provision")
public class EmployeeController {

    @PostMapping("/add")
    public String add(@Valid @RequestBody Employee employeeParam){
        return "OK";
    }

}
