package cns.jdbctemplate.assignment.dao;

import cns.jdbctemplate.assignment.model.Employee;
import cns.jdbctemplate.assignment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDao implements Dao<User> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        return user;
    };

    @Override
    public List<User> list() {
        return jdbcTemplate.query("Call user_list", rowMapper);
    }

    @Override
    public User create(User user) {
        jdbcTemplate.update("CALL save_user(?,?,?)",
                user.getUsername(), user.getEmail(), user.getPassword());
        return user;
    }

    @Override
    public Optional<User> get(long id) {
        return Optional.empty();
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public String delete(long id) {
        return null;
    }

    @Override
    public List<User> findByTitleContaining(String title) {
        return null;
    }

    @Override
    public String deleteAll() {
        return null;
    }




    public Optional<User> findByUsernameAndPassword(String username,String password) {
        User user = null;
        try {
            user = jdbcTemplate.queryForObject("CALL user_name_password(?,?)", rowMapper, username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

}
