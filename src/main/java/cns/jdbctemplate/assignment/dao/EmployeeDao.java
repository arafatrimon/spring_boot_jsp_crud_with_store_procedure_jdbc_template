package cns.jdbctemplate.assignment.dao;

import cns.jdbctemplate.assignment.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDao implements Dao<Employee> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    RowMapper<Employee> rowMapper = (rs, rowNum) -> {
        Employee employee = new Employee();
        employee.setId(rs.getLong("id"));
        employee.setName(rs.getString("name"));
        employee.setFatherName(rs.getString("father_name"));
        employee.setMotherName(rs.getString("mother_name"));
        employee.setGender(rs.getString("gender"));
        employee.setAge(rs.getString("age"));
        employee.setDesignation(rs.getString("designation"));
        employee.setNidNumber(rs.getString("nid_number"));
        employee.setDateOfBirth(rs.getDate("date_of_birth"));
        employee.setContactNumber(rs.getString("contact_number"));
        employee.setHobby(rs.getString("hobby"));
        employee.setAddress(rs.getString("address"));
        return employee;
    };

    @Override
    public List<Employee> list() {
        return jdbcTemplate.query("Call employee_list", rowMapper);
    }

    @Override
    public Employee create(Employee employee) {
        jdbcTemplate.update("CALL save_employee(?,?,?,?,?,?,?,?,?,?,?)",
                employee.getName(),
                employee.getFatherName(),
                employee.getMotherName(),
                employee.getGender(),
                employee.getAge(),
                employee.getDesignation(),
                employee.getNidNumber(),
                employee.getDateOfBirth(),
                employee.getContactNumber(),
                employee.getHobby(),
                employee.getAddress());
        return employee;
    }

    @Override
    public Optional<Employee> get(long id) {
        Employee employee = null;
        try {
            employee = jdbcTemplate.queryForObject("CALL get_employee_by_id(?)", rowMapper, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(employee);
    }

    @Override
    public Employee update(Employee employee) {

        jdbcTemplate.update("CALL update_employee(?,?,?,?,?,?,?,?,?,?,?,?)",
                employee.getName(),
                employee.getFatherName(),
                employee.getMotherName(),
                employee.getGender(),
                employee.getAge(),
                employee.getDesignation(),
                employee.getNidNumber(),
                employee.getDateOfBirth(),
                employee.getContactNumber(),
                employee.getHobby(),
                employee.getAddress(),
                employee.getId());
        return employee;
    }

    @Override
    public String delete(long id) {
        jdbcTemplate.update("CALL delete_student(?)", id);
        return "Employee deleted successfully with id: " + id;
    }

    @Override
    public List<Employee> findByTitleContaining(String title) {
        String query = "SELECT * from EMPLOYEE WHERE NAME LIKE '%" + title + "%'";
        return jdbcTemplate.query(query, rowMapper);
    }

    @Override
    public String deleteAll() {
        String query = "DELETE from employee";
         jdbcTemplate.update(query);
         return "All Data deleted successfully";
    }

}
