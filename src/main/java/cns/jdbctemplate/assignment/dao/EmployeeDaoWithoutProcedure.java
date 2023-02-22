package cns.jdbctemplate.assignment.dao;
import cns.jdbctemplate.assignment.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

    @Repository
    public class EmployeeDaoWithoutProcedure implements Dao<Employee> {

      //  private static final String INSERT_EMPLOYEE_QUERY = "INSERT INTO EMPLOYEE(name,father_name,mother_name,gender,age,designation,nid_number,date_of_birth,contact_number,address) values(?,?,?,?,?,?,?,?,?,?)";
        private static final String UPDATE_EMPLOYEE_BY_ID_QUERY = "UPDATE EMPLOYEE SET name=?,father_name=?,mother_name=?,gender=?,age=?,designation=?,nid_number=?,date_of_birth=?,contact_number=?,address=? WHERE id = ?";
        private static final String GET_EMPLOYEE_BY_ID_QUERY = "SELECT * FROM EMPLOYEE WHERE ID = ?";
        private static final String GET_EMPLOYEES_QUERY = "SELECT * FROM EMPLOYEE";
        private static final String DELETE_EMPLOYEE_BY_ID_QUERY = "DELETE FROM EMPLOYEE WHERE ID = ?";

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
            //employee.setDateOfBirth(rs.getString("date_of_birth"));
            employee.setContactNumber(rs.getString("contact_number"));
            employee.setAddress(rs.getString("address"));
            return employee;
        };

        @Override
        public List<Employee> list() {
            return jdbcTemplate.query(GET_EMPLOYEES_QUERY, rowMapper);
        }

        @Override
        public Employee create(Employee employee) {
            jdbcTemplate.update("CALL save_employee(?,?,?,?,?,?,?,?,?,?)",
                    employee.getName(), employee.getFatherName(),
                    employee.getMotherName(), employee.getGender(),
                    employee.getAge(), employee.getDesignation(),
                    employee.getNidNumber(), employee.getDateOfBirth(),
                    employee.getContactNumber(), employee.getAddress());
            return employee;
        }

        @Override
        public Optional<Employee> get(long id) {
            Employee employee = null;
            try {
                employee = jdbcTemplate.queryForObject(GET_EMPLOYEE_BY_ID_QUERY, rowMapper, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Optional.ofNullable(employee);
        }

        @Override
        public Employee update(Employee employee) {
            jdbcTemplate.update(UPDATE_EMPLOYEE_BY_ID_QUERY,
                    employee.getName(), employee.getFatherName(),
                    employee.getMotherName(), employee.getGender(),
                    employee.getAge(), employee.getDesignation(),
                    employee.getNidNumber(), employee.getDateOfBirth(),
                    employee.getContactNumber(), employee.getAddress(),
                    employee.getId());
            return employee;
        }

        @Override
        public String delete(long id) {
            jdbcTemplate.update(DELETE_EMPLOYEE_BY_ID_QUERY, id);
            return "Employee deleted successfully with id: " + id;
        }

        @Override
        public List<Employee> findByTitleContaining(String title) {
            return null;
        }

        @Override
        public String deleteAll() {
            return null;
        }
    }


