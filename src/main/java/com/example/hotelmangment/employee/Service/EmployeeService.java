package com.example.hotelmangment.employee.Service;


import com.example.hotelmangment.employee.Model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee registerEmployee(Employee employeeDetails);

    Employee getEmployeeById(Long id);

    List<Employee> getAllEmployees();

    void deleteEmployee(Long id);

    Employee updateEmployee(Long id, Employee employeeDetails);

}
