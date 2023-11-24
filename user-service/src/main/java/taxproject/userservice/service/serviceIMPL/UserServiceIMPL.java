package taxproject.userservice.service.serviceIMPL;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taxproject.userservice.dto.EmployeeDTO;
import taxproject.userservice.dto.request.RequestAddEmployee;
import taxproject.userservice.dto.request.RequestEmployeeDeleteDTO;
import taxproject.userservice.dto.request.RequestUserPasswordDTO;
import taxproject.userservice.dto.response.ResponseEmployeeDTO;
import taxproject.userservice.dto.response.ResponseUserAuthDetailsDTO;
import taxproject.userservice.entity.Employee;
import taxproject.userservice.entity.RemovedEmployee;
import taxproject.userservice.entity.UserDetails;
import taxproject.userservice.exception.UserCurrentPasswordDoesntMatchException;
import taxproject.userservice.exception.UserNotFoundException;
import taxproject.userservice.repo.RemoveEmployeeRepo;
import taxproject.userservice.repo.UserDetailsRepo;
import taxproject.userservice.repo.EmployeeRepo;
import taxproject.userservice.service.UserService;
import taxproject.userservice.util.Mappers.UserMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private UserDetailsRepo userDetailsRepo;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RemoveEmployeeRepo removeEmployeeRepo;

    @Override
    public ResponseUserAuthDetailsDTO findUserByUserName(String username,String walletaddress) {
        UserDetails userDetails = userDetailsRepo.findByUsernameEqualsAndWalletAddressEquals(username,walletaddress);
        return new ResponseUserAuthDetailsDTO(userDetails.getUsername(),userDetails.getPassword(),userDetails.getJob_title(),userDetails.getPrivateKey());
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
                    BCrypt.hashpw(requestAddEmployee.getPassword(),BCrypt.gensalt()),
                    requestAddEmployee.getJob_title(),
                    requestAddEmployee.getWalletAddress(),
                    requestAddEmployee.getPrivateKey(),
                    employee
            );
            userDetailsRepo.save(userDetails);
        }
        return employee.getFirst_name()+" saved.";
    }
    @Override
    public String updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = userMapper.EmployeeDTOToEntity(employeeDTO);
        if(employeeRepo.existsById(employeeDTO.getEmployeeID())){
            return employeeRepo.save(employee).getFirst_name()+" updated.";

        }else {
            throw new UserNotFoundException();
        }
    }
    @Override
    public String deleteEmployyee(RequestEmployeeDeleteDTO requestEmployeeDeleteDTO) {
        if(employeeRepo.existsById(requestEmployeeDeleteDTO.getEmployeeId())){
            Employee employee = employeeRepo.getById(requestEmployeeDeleteDTO.getEmployeeId());
            RemovedEmployee removedEmployee = new RemovedEmployee(
                    employee.getNic(),
                    employee.getName_with_initials(),
                    employee.getFirst_name(),
                    employee.getMiddle_name(),
                    employee.getLast_name(),
                    employee.getEmail(),
                    employee.getContact(),
                    employee.getGender(),
                    employee.getAddress(),
                    employee.getDate_of_birth(),
                    employee.getDate_of_appointment(),
                    requestEmployeeDeleteDTO.getReason()
            );
            removeEmployeeRepo.save(removedEmployee);
            employeeRepo.deleteById(requestEmployeeDeleteDTO.getEmployeeId());
            return employee.getFirst_name()+" removed";
        }else {
         throw new UserNotFoundException();
        }
    }
    @Override
    public List<ResponseEmployeeDTO> getAllEmployee() {
        List<Employee> employeeList = employeeRepo.findAll();
        List<ResponseEmployeeDTO> responseEmployeeDTOList = new ArrayList<>();
        for(int i=0 ;i<employeeList.size();i++){
            responseEmployeeDTOList.add(EntityToDTO(employeeList.get(i)));
        }
        return responseEmployeeDTOList;
    }

    @Override
    public ResponseEmployeeDTO getEmployeeNyNIC(String userNIC) {
       Employee employee =  employeeRepo.findByNicEquals(userNIC);
       if(employee != null){
           return EntityToDTO(employee);
       }else {
           throw new UserNotFoundException();
       }
    }

    @Override
    public String updateEmployeePassword(RequestUserPasswordDTO requestUserPasswordDTO) {
        if(employeeRepo.existsById(requestUserPasswordDTO.getUserID())){
            UserDetails userDetails = userDetailsRepo.findByEmpIdEquals(requestUserPasswordDTO.getUserID());
            if(BCrypt.checkpw(requestUserPasswordDTO.getCurrentPassword(),userDetails.getPassword())){
                userDetails.setPassword(BCrypt.hashpw(requestUserPasswordDTO.getNewPassword(),BCrypt.gensalt()));
                userDetailsRepo.save(userDetails);
                return "password updated";
            }else {
                throw new UserCurrentPasswordDoesntMatchException();
            }
        }else {
            throw new UserNotFoundException();
        }
    }

    public ResponseEmployeeDTO EntityToDTO(Employee employee){
        ResponseEmployeeDTO responseEmployeeDTO = new ResponseEmployeeDTO();
        responseEmployeeDTO.setEmployeeID(employee.getEmployeeID());
        responseEmployeeDTO.setNic(employee.getNic());
        responseEmployeeDTO.setName_with_initials(employee.getName_with_initials());
        responseEmployeeDTO.setFirst_name(employee.getFirst_name());
        responseEmployeeDTO.setMiddle_name(employee.getMiddle_name());
        responseEmployeeDTO.setLast_name(employee.getLast_name());
        responseEmployeeDTO.setEmail(employee.getEmail());
        responseEmployeeDTO.setContact(employee.getContact());
        responseEmployeeDTO.setAddress(employee.getAddress());
        responseEmployeeDTO.setGender(employee.getGender());
        responseEmployeeDTO.setDate_of_birth(employee.getDate_of_birth());
        responseEmployeeDTO.setDate_of_appointment(employee.getDate_of_appointment());
        return responseEmployeeDTO;
    }
}
