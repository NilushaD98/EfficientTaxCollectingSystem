package taxproject.userservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseEmployeeDTO {
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
