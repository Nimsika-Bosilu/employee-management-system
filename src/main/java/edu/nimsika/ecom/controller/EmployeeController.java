package edu.nimsika.ecom.controller;

import edu.nimsika.ecom.DTO.EmployeeDto;
import edu.nimsika.ecom.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @GetMapping("/get-all-employees")
    List<EmployeeDto> getAllEmployee(){

        return null;
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrf(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
