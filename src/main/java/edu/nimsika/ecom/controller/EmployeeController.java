package edu.nimsika.ecom.controller;

import edu.nimsika.ecom.DTO.EmployeeDTO;
import edu.nimsika.ecom.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employee")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    @PostMapping("/add")
    EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDto){
        log.info("Hit Employee Controller");
        return employeeService.save(employeeDto);
    }
    @GetMapping("/get-all-employees")
    List<EmployeeDTO> getAllEmployee(){

        return employeeService.getAllEmployee();
    }

}
