package cns.jdbctemplate.assignment.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
//@Table(name = "user")
@Table(uniqueConstraints = { @UniqueConstraint(name = "username_email_constraint",columnNames = { "username", "email" })})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String username;

    private String email;

    private String password;

    @Transient
    private String confirmPassword;



}
