package edu.nimsika.ecom.service;

import edu.nimsika.ecom.DTO.EmployeeDTO;
import edu.nimsika.ecom.model.EmployeeEntity;
import edu.nimsika.ecom.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    public List<EmployeeDTO> getAllEmployee() {

        return employeeRepository.findAll().stream()
                .map(dept -> modelMapper.map(dept, EmployeeDTO.class))
                .toList();
    }

    public EmployeeDTO save(EmployeeDTO employeeDto) {
        return modelMapper.map(
                employeeRepository.save(
                        modelMapper.map(
                                employeeDto, EmployeeEntity.class)), EmployeeDTO.class);
    }
}
