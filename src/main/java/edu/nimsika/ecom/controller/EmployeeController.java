package edu.nimsika.ecom.controller;

import edu.nimsika.ecom.model.EmployeeDto;
import edu.nimsika.ecom.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    @PostMapping("/add")
    EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto){
        log.info(String.valueOf(employeeDto));
        return employeeDto;
    }
}
