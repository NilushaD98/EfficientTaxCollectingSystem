package taxproject.userservice.controller;

import com.sun.xml.bind.api.impl.NameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taxproject.userservice.dto.EmployeeDTO;
import taxproject.userservice.dto.request.RequestAddEmployee;
import taxproject.userservice.dto.request.RequestEmployeeDeleteDTO;
import taxproject.userservice.dto.request.RequestUserPasswordDTO;
import taxproject.userservice.dto.response.ResponseEmployeeDTO;
import taxproject.userservice.dto.response.ResponseUserAuthDetailsDTO;
import taxproject.userservice.service.UserService;
import taxproject.userservice.util.StandardResponse;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/users/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "findUserByUserName/{username}/{walletaddress}")
    public ResponseUserAuthDetailsDTO getUserByUserName(
            @PathVariable(name = "username") String username,
            @PathVariable(name = "walletaddress") String walletAddress

    ){
        System.out.println(username+walletAddress);
        ResponseUserAuthDetailsDTO responseUserAuthDetailsDTO = userService.findUserByUserName(username,walletAddress);
        return responseUserAuthDetailsDTO;
    }
    @PostMapping(path = "addEmployee")
    public ResponseEntity<StandardResponse> addEmployee(@RequestBody RequestAddEmployee requestAddEmployee){
        String save_status = userService.addEmployee(requestAddEmployee);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(202,"User Saved Status :",save_status),HttpStatus.ACCEPTED
        );
    }
    @PutMapping(path="updateEmployee")
    public ResponseEntity<StandardResponse> updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        String update_status = userService.updateEmployee(employeeDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(202,"User Update Status :",update_status),HttpStatus.ACCEPTED
        );
    }
    @DeleteMapping(path = "deleteEmplopyee")

    public ResponseEntity<StandardResponse> deleteEmployee(@RequestBody RequestEmployeeDeleteDTO requestEmployeeDeleteDTO){
        String delete_status = userService.deleteEmployyee(requestEmployeeDeleteDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(202,"User Delete Status : ",delete_status),HttpStatus.ACCEPTED
        );
    }
    @GetMapping(path = "getAllEmployee")
    public ResponseEntity<StandardResponse> getAllEmployee(){
        List<ResponseEmployeeDTO> responseEmployeeDTOList = userService.getAllEmployee();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(202,"All Empolyees",responseEmployeeDTOList),HttpStatus.ACCEPTED
        );
    }
    @GetMapping(path = "getEmployeeByID/{userNIC}")
    public ResponseEntity<StandardResponse> getEmployeeByID(@PathVariable(name = "userNIC") String userNIC){
        ResponseEmployeeDTO responseEmployeeDTO = userService.getEmployeeNyNIC(userNIC);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(202,"Employee "+responseEmployeeDTO.getFirst_name(),responseEmployeeDTO),HttpStatus.ACCEPTED
        );
    }
    @PutMapping(path = "updateUserPassword")
    public ResponseEntity<StandardResponse> updateUserPassword(@RequestBody RequestUserPasswordDTO requestUserPasswordDTO){
        String update_status = userService.updateEmployeePassword(requestUserPasswordDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(202,"Employee Password Update Status : ",update_status),HttpStatus.ACCEPTED
        );
    }
}
