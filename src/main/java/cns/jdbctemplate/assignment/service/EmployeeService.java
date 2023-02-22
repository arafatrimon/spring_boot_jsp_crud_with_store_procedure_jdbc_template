package cns.jdbctemplate.assignment.service;

import cns.jdbctemplate.assignment.dao.EmployeeDao;
import cns.jdbctemplate.assignment.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public List<Employee> list() {
        return employeeDao.list();
    }

    public Employee create(Employee employee) {
        employeeDao.create(employee);
        return employee;
    }

    public Optional<Employee> get(long id) {
        Optional<Employee> employee = null;
        try {
            employee = employeeDao.get(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }
    public Employee update(Employee employee) {
        employeeDao.update(employee);
        return employee;
    }

    public String delete(long id) {
        employeeDao.delete(id);
        return "Employee deleted successfully with id: " + id;
    }


    public List<Employee> findByTitleContaining(String title) {
        return employeeDao.findByTitleContaining(title);
    }

    public String deleteAll() {
        employeeDao.deleteAll();
        return "All Data deleted successfully";
    }




}
