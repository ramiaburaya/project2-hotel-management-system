package com.example.hotelmangment.employee.Service;

import com.example.hotelmangment.employee.Model.Employee;
import com.example.hotelmangment.employee.EmployeeException.EmployeeNotFoundException;
import com.example.hotelmangment.employee.Model.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee registerEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepository.delete(existingEmployee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

        existingEmployee.setFirstName(employeeDetails.getFirstName());
        existingEmployee.setLastName(employeeDetails.getLastName());
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setPhoneNumber(employeeDetails.getPhoneNumber());
        existingEmployee.setAddress(employeeDetails.getAddress());
        existingEmployee.setRole(employeeDetails.getRole());

        return employeeRepository.save(employeeDetails);
    }
}
