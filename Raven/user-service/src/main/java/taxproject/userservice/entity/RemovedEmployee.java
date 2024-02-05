package taxproject.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "removedEmployee")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RemovedEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long removedEmployeeID;

    @Column(nullable = false,unique = true,length = 12)
    private String removedEmployeeNic;

    @Column(nullable = false)
    private String removedEmployeeName_with_initials;

    @Column(nullable = false)
    private String removedEmployeeFirst_name;

    @Column(nullable = true)
    private String removedEmployeeMiddle_name;

    @Column(nullable = false)
    private String removedEmployeeLast_name;

    @Column(nullable = false,unique = true)
    private String removedEmployeeEmail;

    @Column(nullable = false,unique = true)
    private String removedEmployeeContact;

    @Column(nullable = false)
    private String removedEmployeeGender;

    @Column(nullable = false)
    private String removedEmployeeAddress;

    @Column(nullable = false)
    private Date removedEmployeeDate_of_birth;

    @Column(nullable = false)
    private Date removedEmployeeDate_of_appointment;
    @Column(nullable = false)
    private String reason;

    public RemovedEmployee(String removedEmployeeNic, String removedEmployeeName_with_initials, String removedEmployeeFirst_name, String removedEmployeeMiddle_name, String removedEmployeeLast_name, String removedEmployeeEmail, String removedEmployeeContact, String removedEmployeeGender, String removedEmployeeAddress, Date removedEmployeeDate_of_birth, Date removedEmployeeDate_of_appointment, String reason) {
        this.removedEmployeeNic = removedEmployeeNic;
        this.removedEmployeeName_with_initials = removedEmployeeName_with_initials;
        this.removedEmployeeFirst_name = removedEmployeeFirst_name;
        this.removedEmployeeMiddle_name = removedEmployeeMiddle_name;
        this.removedEmployeeLast_name = removedEmployeeLast_name;
        this.removedEmployeeEmail = removedEmployeeEmail;
        this.removedEmployeeContact = removedEmployeeContact;
        this.removedEmployeeGender = removedEmployeeGender;
        this.removedEmployeeAddress = removedEmployeeAddress;
        this.removedEmployeeDate_of_birth = removedEmployeeDate_of_birth;
        this.removedEmployeeDate_of_appointment = removedEmployeeDate_of_appointment;
        this.reason = reason;
    }
}
