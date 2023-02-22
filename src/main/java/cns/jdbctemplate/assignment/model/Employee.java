package cns.jdbctemplate.assignment.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name is required")
    @Column(name = "name")
    private String name;


    @NotBlank(message = "Father is required")
    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "gender")
    private String gender;

    @NotBlank(message = "Age is required")
    @Size(min = 0,max = 2,message = "Between 0 and 2 required")
    @Column(name = "age")
    private String age;

    @Column(name = "designation")
    private String designation;

    @Column(name = "nid_number")
    private String nidNumber;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "hobby")
    private String hobby;

    @Column(name = "address")
    private String address;

}



