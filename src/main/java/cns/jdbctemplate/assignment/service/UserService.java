package cns.jdbctemplate.assignment.service;

import cns.jdbctemplate.assignment.dao.UserDao;
import cns.jdbctemplate.assignment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

@Autowired
private UserDao userDao;


    public List<User> list() {
        return userDao.list();
    }

    public User create(User user) {
        userDao.create(user);
        return user;
    }

   public Optional<User> findByUsernameAndPassword(String username, String password){
       return  userDao.findByUsernameAndPassword(username,password);
   }
}
