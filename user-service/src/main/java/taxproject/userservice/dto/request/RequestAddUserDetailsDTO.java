package taxproject.userservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import taxproject.userservice.entity.Employee;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestAddUserDetailsDTO {


    private String username;
    private String password;
    private Employee emp_Id;
}
