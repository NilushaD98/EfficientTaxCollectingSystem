package taxproject.userservice.service.serviceIMPL;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import taxproject.userservice.dto.request.RequestAddEmployee;
import taxproject.userservice.dto.response.ResponseUserAuthDetailsDTO;
import taxproject.userservice.entity.Employee;
import taxproject.userservice.entity.UserDetails;
import taxproject.userservice.repo.UserDetailsRepo;
import taxproject.userservice.repo.EmployeeRepo;
import taxproject.userservice.service.UserService;


@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private UserDetailsRepo userDetailsRepo;


    @Override
    public ResponseUserAuthDetailsDTO findUserByUserName(String username) {
        UserDetails userDetails = userDetailsRepo.findByUsernameEquals(username);
        Employee employee = employeeRepo.findById(userDetails.getEmp_Id().getEmployeeID()).get();

        return null;
    }
    @Override
    public String addEmployee(RequestAddEmployee requestAddEmployee) {
        Employee employee = new Employee(
                requestAddEmployee.getNic(),
                requestAddEmployee.getName_with_initials(),
                requestAddEmployee.getFirst_name(),
                requestAddEmployee.getMiddle_name(),
                requestAddEmployee.getLast_name(),
                requestAddEmployee.getEmail(),
                requestAddEmployee.getContact(),
                requestAddEmployee.getGender(),
                requestAddEmployee.getAddress(),
                requestAddEmployee.getDate_of_birth(),
                requestAddEmployee.getDate_of_appointment()
        );
        employeeRepo.save(employee);
        if(employee.getEmployeeID() != null){
            UserDetails userDetails = new UserDetails(
                    requestAddEmployee.getUsername(),
                    requestAddEmployee.getPassword(),
                    requestAddEmployee.getJob_title(),
                    employee
            );
            userDetailsRepo.save(userDetails);
        }
        return employee.getFirst_name()+" saved.";
    }
}
