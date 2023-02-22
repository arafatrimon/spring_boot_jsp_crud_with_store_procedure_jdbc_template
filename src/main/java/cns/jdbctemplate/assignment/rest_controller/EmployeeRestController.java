package cns.jdbctemplate.assignment.rest_controller;

import cns.jdbctemplate.assignment.model.Employee;
import cns.jdbctemplate.assignment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RestController
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeService.create(employee);
    }

    @GetMapping("/list")
    public List<Employee> employeeList(){
        return employeeService.list();
    }

    @GetMapping("/employee/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable("id") long id){
        return employeeService.get(id);
    }

    @PostMapping("/update")
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeService.update(employee);
    }

    @DeleteMapping("/delete/{id}")
public String deleteEmployee(@PathVariable("id") long id){
        return employeeService.delete(id);
    }
}
