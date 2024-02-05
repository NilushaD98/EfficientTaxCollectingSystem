package taxproject.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.control.NoComplexMapping;
import taxproject.userservice.dto.enu.Roles;
import taxproject.userservice.entity.UserDetails;

import javax.persistence.*;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {

    private Long employeeID;
    private String nic;
    private String name_with_initials;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String email;
    private String contact;
    private String gender;
    private String address;
    private Date date_of_birth;
    private Date date_of_appointment;


}
