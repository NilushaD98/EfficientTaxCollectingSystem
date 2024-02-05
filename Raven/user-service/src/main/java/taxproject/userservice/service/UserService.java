package taxproject.userservice.service;

import taxproject.userservice.dto.EmployeeDTO;
import taxproject.userservice.dto.request.RequestAddEmployee;
import taxproject.userservice.dto.request.RequestEmployeeDeleteDTO;
import taxproject.userservice.dto.request.RequestUserPasswordDTO;
import taxproject.userservice.dto.response.ResponseEmployeeDTO;
import taxproject.userservice.dto.response.ResponseUserAuthDetailsDTO;

import java.util.List;

public interface UserService {
    ResponseUserAuthDetailsDTO findUserByUserName(String username,String walletaddress);

    String addEmployee(RequestAddEmployee requestAddEmployee);

    String updateEmployee(EmployeeDTO employeeDTO);

    String deleteEmployyee(RequestEmployeeDeleteDTO requestEmployeeDeleteDTO);

    List<ResponseEmployeeDTO> getAllEmployee();

    ResponseEmployeeDTO getEmployeeNyNIC(String userNIC);

    String updateEmployeePassword(RequestUserPasswordDTO requestUserPasswordDTO);
}
