package taxproject.userservice.controller;

import com.sun.xml.bind.api.impl.NameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taxproject.userservice.dto.request.RequestAddEmployee;
import taxproject.userservice.dto.response.ResponseUserAuthDetailsDTO;
import taxproject.userservice.service.UserService;
import taxproject.userservice.util.StandardResponse;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "findUserByUserName/{username}")
    public ResponseUserAuthDetailsDTO getUserByUserName(
            @PathVariable(name = "username") String username
    ){
        ResponseUserAuthDetailsDTO responseUserAuthDetailsDTO = userService.findUserByUserName(username);
        System.out.println(responseUserAuthDetailsDTO);
        return responseUserAuthDetailsDTO;
    }
    @PostMapping(path = "addEmployee")
    public ResponseEntity<StandardResponse> addEmployee(@RequestBody RequestAddEmployee requestAddEmployee){
        String save_status = userService.addEmployee(requestAddEmployee);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"User Saved Status :",save_status),HttpStatus.ACCEPTED
        );
    }
    @GetMapping("test")
    public String fsdf(){
        return "hey";
    }


}
