package taxproject.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeID;

    @Column(nullable = false,unique = true,length = 12)
    private String nic;

    @Column(nullable = false)
    private String name_with_initials;

    @Column(nullable = false)
    private String first_name;

    @Column(nullable = true)
    private String middle_name;

    @Column(nullable = false)
    private String last_name;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String contact;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Date date_of_birth;

    @Column(nullable = false)
    private Date date_of_appointment;

    @OneToOne(mappedBy = "empId")
    private UserDetails userDetails;

    public Employee(String nic, String name_with_initials, String first_name, String middle_name, String last_name, String email, String contact, String gender, String address, Date date_of_birth, Date date_of_appointment) {
        this.nic = nic;
        this.name_with_initials = name_with_initials;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.email = email;
        this.contact = contact;
        this.gender = gender;
        this.address = address;
        this.date_of_birth = date_of_birth;
        this.date_of_appointment = date_of_appointment;
    }
}
